package br.com.village.controllers.rest;

import br.com.village.controllers.service.FinancialReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/financialReport")
public class FinancialReportRest {
    private FinancialReportService financialReportService;

    public FinancialReportRest(FinancialReportService financialReportService) {
        this.financialReportService = financialReportService;
    }

    @GetMapping("/")
    public ResponseEntity getFinancialReport() {
        return ResponseEntity.ok(financialReportService.getFinancialReport());
    }

}
