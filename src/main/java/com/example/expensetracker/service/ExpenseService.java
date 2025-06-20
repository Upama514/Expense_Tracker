package com.example.expensetracker.service;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.payload.ExpenseRequest;
import com.example.expensetracker.payload.DashboardResponse;
import com.example.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense createExpense(String userId, ExpenseRequest request) {
        Expense expense = new Expense();
        expense.setUserId(userId);
        expense.setCategory(request.getCategory());
        expense.setDescription(request.getDescription());
        expense.setAmount(request.getAmount());
        expense.setDate(request.getDate());

        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses(String userId) {
        return expenseRepository.findByUserId(userId);
    }

    public Expense updateExpense(String id, String userId, ExpenseRequest request) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (!expense.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        expense.setCategory(request.getCategory());
        expense.setDescription(request.getDescription());
        expense.setAmount(request.getAmount());
        expense.setDate(request.getDate());

        return expenseRepository.save(expense);
    }

    public void deleteExpense(String id, String userId) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (!expense.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        expenseRepository.delete(expense);
    }

    public List<Expense> filterByCategory(String userId, String category) {
        return expenseRepository.findByUserIdAndCategory(userId, category);
    }

    public List<Expense> filterByDateRange(String userId, LocalDate from, LocalDate to) {
        return expenseRepository.findByUserIdAndDateBetween(userId, from, to);
    }

    // ✅ New: Dashboard summary method
    public DashboardResponse getDashboard(String userId) {
        List<Expense> expenses = expenseRepository.findByUserId(userId);

        double total = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        double totalThisMonth = expenses.stream()
                .filter(e -> e.getDate().getMonthValue() == LocalDate.now().getMonthValue()
                        && e.getDate().getYear() == LocalDate.now().getYear())
                .mapToDouble(Expense::getAmount)
                .sum();

        Map<String, Double> byCategory = expenses.stream()
                .collect(Collectors.groupingBy(
                        Expense::getCategory,
                        Collectors.summingDouble(Expense::getAmount)
                ));

        List<Expense> recentExpenses = expenses.stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .limit(5)
                .toList();

        DashboardResponse response = new DashboardResponse();
        response.setTotalAmount(total);
        response.setTotalThisMonth(totalThisMonth);
        response.setByCategory(byCategory);
        response.setRecentExpenses(recentExpenses);
        return response;
    }
}
