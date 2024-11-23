package com.project.lms.ExpenseTrackerLms.services.stats;


import com.project.lms.ExpenseTrackerLms.dto.GraphDTO;
import com.project.lms.ExpenseTrackerLms.dto.StatsDTO;
import com.project.lms.ExpenseTrackerLms.entity.Expense;
import com.project.lms.ExpenseTrackerLms.entity.Income;
import com.project.lms.ExpenseTrackerLms.repository.ExpenseRepository;
import com.project.lms.ExpenseTrackerLms.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor

public class StatsServiceImpl implements StatsService {

    private final IncomeRepository incomeRepository;

    private final ExpenseRepository expenseRepository;

//    public GraphDTO getChartData(){
//        LocalDate endDate = LocalDate.now();
//        LocalDate startDate = endDate.minusDays(27);
//
//        GraphDTO graphDTO = new GraphDTO();
//        graphDTO.setExpenseList(expenseRepository.findByDateBetween(startDate,endDate));
//         iterate over the list, and add up all the amounts
//        and send it over through the response

//        graphDTO.setIncomeList(incomeRepository.findByDateBetween(startDate, endDate));
//
    /*
    *               {
    *       monthlyIncome: ''
    *       expense: ''
    *   }
    *
    *
    * */


//        return graphDTO;
//    }

    public GraphDTO getChartData(Long userId){
        GraphDTO graphDTO = new GraphDTO();
        graphDTO.setExpenseList(expenseRepository.findByUserId(userId));
        graphDTO.setIncomeList(incomeRepository.findByUserId(userId));

        return graphDTO;
    }

    public StatsDTO getStats(Long userId){
        Double totalIncome = incomeRepository.sumAllAmounts(userId);
        Double totalExpense = expenseRepository.sumAllAmounts(userId);

        Optional<Income> optionalIncome = incomeRepository.findFirstByOrderByDateDesc(userId);
        Optional<Expense> optionalExpense = expenseRepository.findFirstByOrderByDateDesc(userId);

        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setExpense(totalExpense);
        statsDTO.setIncome(totalIncome);

        optionalIncome.ifPresent(statsDTO::setLatestIncome);
        optionalExpense.ifPresent(statsDTO::setLatestExpense);

        statsDTO.setBalance(totalIncome-totalExpense);

        List<Income> incomeList = incomeRepository.findByUserId(userId);
        List<Expense> expenseList = expenseRepository.findByUserId(userId);

        OptionalDouble minIncome = incomeList.stream().mapToDouble(Income :: getAmount).min();
        OptionalDouble maxIncome = incomeList.stream().mapToDouble(Income::getAmount).max();

        OptionalDouble minExpense = expenseList.stream().mapToDouble(Expense::getAmount).min();
        OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expense::getAmount).max();

        statsDTO.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : null);
        statsDTO.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : null);

        statsDTO.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble() : null);
        statsDTO.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble() : null);


        return statsDTO;
    }
}


