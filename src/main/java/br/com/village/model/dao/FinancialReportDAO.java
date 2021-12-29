package br.com.village.model.dao;

import br.com.village.model.transport.FinancialReportDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Repository
public class FinancialReportDAO {
    private Connection connection;

    public FinancialReportDAO() throws SQLException {
        this.connection = new ConnectionFactoryJDBC().getConnection();
    }

    public FinancialReportDTO getLastFinancialReport() throws SQLException {
        String sql = "SELECT * FROM village_manager.financial_report ORDER BY fr_id DESC LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new FinancialReportDTO(
                        rs.getInt("fr_id"),
                        LocalDate.parse(rs.getString("fr_date")),
                        rs.getDouble("fr_remaining_budget"),
                        rs.getDouble("fr_total_expenses"),
                        rs.getDouble("fr_total_budget"),
                        rs.getInt("fr_most_expensive_villager_id")
                );
            }
        }
        return null;
    }

    public ResponseEntity saveFinancialReport(FinancialReportDTO financialReportDTO) throws SQLException {
        String sql = "INSERT INTO village_manager.financial_report (fr_date, fr_remaining_budget, fr_total_expenses, fr_total_budget, fr_most_expensive_villager_id) VALUES (?::date, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, String.valueOf(financialReportDTO.getDate()));
            stmt.setDouble(2, financialReportDTO.getRemainingBudget());
            stmt.setDouble(3, financialReportDTO.getExpenses());
            stmt.setDouble(4, financialReportDTO.getTotalBudget());
            stmt.setInt(5, financialReportDTO.getMostExpensiveVillagerId());
            stmt.execute();
            return ResponseEntity.ok().build();
        }
    }
}
