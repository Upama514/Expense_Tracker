package com.example.expensetracker.payload;

import com.example.expensetracker.model.Expense;
import java.util.List;
import java.util.Map;

public class DashboardResponse {
    private double totalAmount;
    private double totalThisMonth;
    private Map<String, Double> byCategory;
    private List<Expense> recentExpenses;

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalThisMonth() {
        return totalThisMonth;
    }

    public void setTotalThisMonth(double totalThisMonth) {
        this.totalThisMonth = totalThisMonth;
    }

    public Map<String, Double> getByCategory() {
        return byCategory;
    }

    public void setByCategory(Map<String, Double> byCategory) {
        this.byCategory = byCategory;
    }

    public List<Expense> getRecentExpenses() {
        return recentExpenses;
    }

    public void setRecentExpenses(List<Expense> recentExpenses) {
        this.recentExpenses = recentExpenses;
    }
}
