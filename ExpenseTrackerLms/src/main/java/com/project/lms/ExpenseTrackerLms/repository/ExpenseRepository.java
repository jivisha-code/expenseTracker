package com.project.lms.ExpenseTrackerLms.repository;

import com.project.lms.ExpenseTrackerLms.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(e.amount) FROM Expense e where userId = (:userId)")
    Double sumAllAmounts(Long userId);

    @Query(value = " from Expense where userId = (:userId) order by date desc limit 1 ")
    Optional<Expense> findFirstByOrderByDateDesc(Long userId);

    List<Expense> findByUserId(Long userId);
}
