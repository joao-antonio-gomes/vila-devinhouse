package br.com.village.controllers.service;

import br.com.village.model.dao.FinancialReportDAO;
import br.com.village.model.transport.FinancialReportDTO;
import br.com.village.model.transport.ResidentsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class FinancialReportService {
    private FinancialReportDAO financialReportDAO;

    public FinancialReportService(FinancialReportDAO financialReportDAO) {
        this.financialReportDAO = financialReportDAO;
    }

    public FinancialReportDTO getFinancialReport() {
        try {
            ResidentsService residentsService = new ResidentsService();
            double totalRent = residentsService.getTotalRent();
            ResidentsDTO moreExpensiveVillager = residentsService.getMoreExpensiveVillager();
            FinancialReportDTO financialReportDTO = new FinancialReportDTO(totalRent, moreExpensiveVillager.getId());
            this.financialReportDAO.saveFinancialReport(financialReportDTO);
            return this.financialReportDAO.getLastFinancialReport();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
