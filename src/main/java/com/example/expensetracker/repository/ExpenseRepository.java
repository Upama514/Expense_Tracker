package com.example.expensetracker.repository;

import com.example.expensetracker.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends MongoRepository<Expense, String> {
    List<Expense> findByUserId(String userId);
    List<Expense> findByUserIdAndCategory(String userId, String category);
    List<Expense> findByUserIdAndDateBetween(String userId, LocalDate from, LocalDate to);
}
