package com.ExpenseTracker.ExpenseTracker.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ExpenseTracker.ExpenseTracker.Entity.ExpenseEntity;
import com.ExpenseTracker.ExpenseTracker.Exceptions.ExpenseWereAlreadyThereExceptions;
import com.ExpenseTracker.ExpenseTracker.Repo.ExpenseRepo;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepo expenseRepo;

    public ExpenseService(ExpenseRepo expenseRepo) {
        this.expenseRepo = expenseRepo;
    }

    public ExpenseEntity saveExpense(ExpenseEntity entity) {

        if (entity.getUserId() != null) {
            throw new ExpenseWereAlreadyThereExceptions("User were already present");
        }
        return expenseRepo.save(entity);
    }

    public List<ExpenseEntity> getAll() {
        return expenseRepo.findAll();
    }

    public List<ExpenseEntity> findByDate(String date) {
        List<ExpenseEntity> list = new ArrayList<>();
        List<ExpenseEntity> expenses = expenseRepo.findAll();
        boolean isInFormat = date.matches("\\\\d{4}-\\\\d{2}-\\\\d{2}");

        for (ExpenseEntity expense : expenses) {
            String cdate = expense.getDate().toString();
            if (isInFormat && cdate.equals(date)) {
                list.add(expense);
            }
        }
        return list;
    }

    public String deleteExpense(Long userId) {

        expenseRepo.deleteById(userId);

        return "user were deleted";
    }

    public ExpenseEntity updateExpense(ExpenseEntity expenseEntity, Long userId) {
        ExpenseEntity existingExpense = expenseRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (expenseEntity.getDescription() != null) {
            existingExpense.setDescription(expenseEntity.getDescription());
        }

        if (expenseEntity.getAmmount() != null) {
            existingExpense.setAmmount(expenseEntity.getAmmount());
        }

        if (expenseEntity.getDate() != null) {
            existingExpense.setDate(expenseEntity.getDate());
        }

        return expenseRepo.save(existingExpense);
    }
}
