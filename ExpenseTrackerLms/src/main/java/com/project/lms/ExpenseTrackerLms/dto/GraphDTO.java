package com.project.lms.ExpenseTrackerLms.dto;

import com.project.lms.ExpenseTrackerLms.entity.Expense;
import com.project.lms.ExpenseTrackerLms.entity.Income;
import lombok.Data;

import java.util.List;

@Data
public class GraphDTO {

    private List<Expense> expenseList;

    private List<Income> incomeList;
}
