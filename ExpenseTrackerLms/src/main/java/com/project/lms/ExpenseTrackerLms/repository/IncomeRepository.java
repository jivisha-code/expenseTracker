package com.project.lms.ExpenseTrackerLms.repository;


import com.project.lms.ExpenseTrackerLms.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(i.amount) FROM Income i where userId = (:userId)")  /* Hibernate Query Language */
    Double sumAllAmounts(Long userId);

    @Query(value = " from Income where userId = (:userId) order by date desc limit 1 ")
    Optional<Income> findFirstByOrderByDateDesc(Long userId);

    List<Income> findByUserId(Long userId);
}


