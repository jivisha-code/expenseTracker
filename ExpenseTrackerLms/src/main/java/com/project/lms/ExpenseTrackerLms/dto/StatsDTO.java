package com.project.lms.ExpenseTrackerLms.dto;

import com.project.lms.ExpenseTrackerLms.entity.Expense;
import com.project.lms.ExpenseTrackerLms.entity.Income;
import lombok.Data;

@Data
public class StatsDTO {

    private Double income;

    private Double expense;

    private Income latestIncome;

    private Expense latestExpense;

    private Double balance;

    private Double minIncome;

    private Double maxIncome;

    private Double minExpense;

    private Double maxExpense;

    private Long userId;
}
