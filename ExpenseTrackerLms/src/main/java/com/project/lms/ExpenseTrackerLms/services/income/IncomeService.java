package com.project.lms.ExpenseTrackerLms.services.income;


import com.project.lms.ExpenseTrackerLms.dto.IncomeDTO;
import com.project.lms.ExpenseTrackerLms.entity.Income;

import java.util.List;

public interface IncomeService {

    Income postIncome(IncomeDTO incomeDTO);

    List<IncomeDTO> getAllIncomes();

    Income updateIncome(IncomeDTO incomeDTO);

    IncomeDTO getIncomeById(Long id);

    void deleteIncome(Long id);
    List<Income> getIncomeByUserId(Long userId);  // Add this line
    void deleteAllByUserId(Long userId);  // Add this line
}
