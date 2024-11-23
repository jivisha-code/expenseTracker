//package com.project.lms.ExpenseTrackerLms.services.expense;
//
//import com.project.lms.ExpenseTrackerLms.dto.ExpenseDTO;
//import com.project.lms.ExpenseTrackerLms.entity.Expense;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//public interface ExpenseService {
//    Expense postExpense(ExpenseDTO expenseDTO);
//
//    List<Expense> getAllExpenses();
//
//
//    Expense getExpenseById(Long id);
//
//    Expense updateExpense(Long id, ExpenseDTO expenseDTO);
//
//    void deleteExpense(Long id);
//
//    List<Expense> getExpenseByUserId(Long userId);
//
//
//}

package com.project.lms.ExpenseTrackerLms.services.expense;

import com.project.lms.ExpenseTrackerLms.dto.ExpenseDTO;
import com.project.lms.ExpenseTrackerLms.entity.Expense;

import java.util.List;

public interface ExpenseService {

    Expense postExpense(ExpenseDTO expenseDTO);

    Expense updateExpense(ExpenseDTO expenseDTO);

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    void deleteExpense(Long id);

    List<Expense> getExpenseByUserId(Long userId); // Add this method
    void deleteExpenseByUserId(Long expenseId); // Add this method
}

