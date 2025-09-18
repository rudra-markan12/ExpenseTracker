package com.ExpenseTracker.ExpenseTracker.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ExpenseTracker.ExpenseTracker.Entity.ExpenseEntity;

@Repository
public interface ExpenseRepo extends JpaRepository<ExpenseEntity , Long> {

}
