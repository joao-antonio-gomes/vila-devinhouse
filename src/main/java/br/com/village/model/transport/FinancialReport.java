package br.com.village.model.transport;

import java.util.Date;

public class FinancialReport {
    private Integer id;
    private Date date;
    private Double remainingBudget;
    private Double totalExpenses;
    private Double totalBudget;
    private Integer mostExpensiveVillagerId;

    public FinancialReport(Date date, Double remainingBudget, Double totalExpenses, Double totalBudget, Integer mostExpensiveVillagerId) {
        this.date = date;
        this.remainingBudget = remainingBudget;
        this.totalExpenses = totalExpenses;
        this.totalBudget = totalBudget;
        this.mostExpensiveVillagerId = mostExpensiveVillagerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getRemainingBudget() {
        return remainingBudget;
    }

    public void setRemainingBudget(Double remainingBudget) {
        this.remainingBudget = remainingBudget;
    }

    public Double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(Double totalExpenses) {
        this.totalExpenses = totalExpenses;
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
