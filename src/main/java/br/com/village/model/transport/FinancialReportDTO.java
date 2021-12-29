package br.com.village.model.transport;

import br.com.village.config.VillageConfig;

import java.time.LocalDate;

public class FinancialReportDTO {
    private Integer id;
    private LocalDate date;
    private Double remainingBudget;
    private Double expenses;
    private Double totalBudget;
    private Integer mostExpensiveVillagerId;

    public FinancialReportDTO(Double expenses, Integer mostExpensiveVillagerId) {
        this.date = LocalDate.now();
        this.expenses = expenses;
        this.totalBudget = VillageConfig.getBudget();
        this.remainingBudget = this.totalBudget - this.expenses;
        this.mostExpensiveVillagerId = mostExpensiveVillagerId;
    }

    public FinancialReportDTO(Integer id, LocalDate date, Double remainingBudget, Double expenses, Double totalBudget, Integer mostExpensiveVillagerId) {
        this.id = id;
        this.date = date;
        this.remainingBudget = remainingBudget;
        this.expenses = expenses;
        this.totalBudget = totalBudget;
        this.mostExpensiveVillagerId = mostExpensiveVillagerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getRemainingBudget() {
        return remainingBudget;
    }

    public void setRemainingBudget(Double remainingBudget) {
        this.remainingBudget = remainingBudget;
    }

    public Double getExpenses() {
        return expenses;
    }

    public void setExpenses(Double expenses) {
        this.expenses = expenses;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Integer getMostExpensiveVillagerId() {
        return mostExpensiveVillagerId;
    }

    public void setMostExpensiveVillagerId(Integer mostExpensiveVillagerId) {
        this.mostExpensiveVillagerId = mostExpensiveVillagerId;
    }
}
