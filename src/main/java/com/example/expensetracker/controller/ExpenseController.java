package com.example.expensetracker.controller;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.payload.DashboardResponse;
import com.example.expensetracker.payload.ExpenseRequest;
import com.example.expensetracker.service.ExpenseService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public Expense createExpense(@RequestBody ExpenseRequest request, Principal principal) {
        return expenseService.createExpense(principal.getName(), request);
    }

    @GetMapping
    public List<Expense> getAllExpenses(Principal principal) {
        return expenseService.getAllExpenses(principal.getName());
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable String id, @RequestBody ExpenseRequest request, Principal principal) {
        return expenseService.updateExpense(id, principal.getName(), request);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable String id, Principal principal) {
        expenseService.deleteExpense(id, principal.getName());
    }

    @GetMapping("/filter/category")
    public List<Expense> filterByCategory(@RequestParam String category, Principal principal) {
        return expenseService.filterByCategory(principal.getName(), category);
    }

    @GetMapping("/filter/date")
    public List<Expense> filterByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            Principal principal
    ) {
        return expenseService.filterByDateRange(principal.getName(), from, to);
    }

    @GetMapping("/export/csv")
    public void exportToCsv(HttpServletResponse response, Principal principal) throws IOException {
        List<Expense> expenses = expenseService.getAllExpenses(principal.getName());

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=expenses.csv");

        try (PrintWriter writer = response.getWriter()) {
            writer.println("Date,Category,Description,Amount");
            for (Expense expense : expenses) {
                writer.printf("%s,%s,%s,%.2f%n",
                        expense.getDate(),
                        expense.getCategory(),
                        expense.getDescription(),
                        expense.getAmount()
                );
            }
        }
    }

    // New Dashboard endpoint
    @GetMapping("/dashboard")
    public DashboardResponse getDashboard(Principal principal) {
        return expenseService.getDashboard(principal.getName());
    }
}
