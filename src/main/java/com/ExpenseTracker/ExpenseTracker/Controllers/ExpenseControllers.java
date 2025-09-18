package com.ExpenseTracker.ExpenseTracker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ExpenseTracker.ExpenseTracker.Entity.ExpenseEntity;
import com.ExpenseTracker.ExpenseTracker.Services.ExpenseService;

@RestController
@RequestMapping("/expense")
public class ExpenseControllers {

    @Autowired
    private ExpenseService expenseService;

    public ExpenseControllers(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<?> addExpense(@RequestBody ExpenseEntity expenseEntity) {
        return new ResponseEntity<>(expenseService.saveExpense(expenseEntity), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(expenseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{date}")
    public ResponseEntity<?> findByDate(@PathVariable String date){
        return new ResponseEntity<>(expenseService.findByDate(date), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long userId){
        return new ResponseEntity<>(expenseService.deleteExpense(userId), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateExpense(@RequestBody ExpenseEntity expenseEntity , Long userId){
        return new ResponseEntity<>(expenseService.updateExpense(expenseEntity, userId), HttpStatus.OK);
    }

}
