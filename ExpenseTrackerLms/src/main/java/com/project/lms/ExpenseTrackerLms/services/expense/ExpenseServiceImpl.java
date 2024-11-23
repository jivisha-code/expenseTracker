package com.project.lms.ExpenseTrackerLms.services.expense;

import com.project.lms.ExpenseTrackerLms.dto.ExpenseDTO;
import com.project.lms.ExpenseTrackerLms.entity.Expense;
import com.project.lms.ExpenseTrackerLms.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService{

    private final ExpenseRepository expenseRepository;


    public Expense postExpense(ExpenseDTO expenseDTO){

        return saveOrUpdateExpense(new Expense(), expenseDTO);
    }

    private Expense saveOrUpdateExpense(Expense expense, ExpenseDTO expenseDTO){
        expense.setTitle(expenseDTO.getTitle());
//        expense.setDate(expenseDTO.getDate());
        expense.setDate(ObjectUtils.isEmpty(expenseDTO.getDate()) ? LocalDate.now() :expenseDTO.getDate());
//        expense.setAmount(expenseDTO.getAmount());
        expense.setAmount(ObjectUtils.isEmpty(expenseDTO.getAmount()) ? 0 :expenseDTO.getAmount());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDescription(expenseDTO.getDescription());
        expense.setUserId(expenseDTO.getUserId());

        return expenseRepository.save(expense);
    }

//    public Expense updateExpense(Long id, ExpenseDTO expenseDTO){
//        Optional<Expense> optionalExpense = expenseRepository.findById(id);
//        if(optionalExpense.isPresent()){
//            return saveOrUpdateExpense(optionalExpense.get(), expenseDTO);
//        }else{
//            throw new EntityNotFoundException("Expense is not present with id " + id);
//        }
//    }

    public Expense updateExpense(ExpenseDTO expenseDTO){
        // Find the expense by ID and User ID
        Optional<Expense> optionalExpense = expenseRepository.findById(expenseDTO.getExpenseId());

        // Check if the expense exists for the given user
        if (optionalExpense.isPresent()) {
            // Update the expense details and save it
            return saveOrUpdateExpense(optionalExpense.get(), expenseDTO);
        } else {
            // Throw an exception if the expense is not found
            throw new EntityNotFoundException("Expense not found with id " + expenseDTO.getExpenseId() + " for userId " + expenseDTO.getUserId());
        }
    }



    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll().stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }
        public Expense getExpenseById(Long id){
            Optional<Expense> optionalExpense = expenseRepository.findById(id);
            if(optionalExpense.isPresent()){
                return optionalExpense.get();
            }else{
                throw new EntityNotFoundException("Expense is not present with id " + id);
            }

        }

//    public void deleteExpense(Long id){
//        Optional<Expense> optionalExpense = expenseRepository.findById(id);
//        if(OptionalExpense.isPresent()){
//            expenseRepository.deleteById(id);
//        }else{
//            throw new EntityNotFoundException("Expense is not present with id " + id);
//        }
//    }
public void deleteExpense(Long id) {
    Optional<Expense> optionalExpense = expenseRepository.findById(id);
    if (optionalExpense.isPresent()) { // Change OptionalExpense to optionalExpense
        expenseRepository.deleteById(id);
    } else {
        throw new EntityNotFoundException("Expense is not present with id " + id);
    }
}

    @Override
    public void deleteExpenseByUserId(Long expenseId) {
        Optional<Expense> expenses = expenseRepository.findById(expenseId);
        if (expenses.isEmpty()) {
            throw new EntityNotFoundException("No expense records found for user with ID: " + expenseId);
        }
        expenseRepository.deleteById(expenseId); // Deletes all expenses for the user
    }


//    @Override
//    public List<Expense> getExpenseByUserId(Long userId) {
//        List<Expense> expenseList = expenseRepository.findByUserId(userId);
//        return expenseList;
//    }

    @Override
    public List<Expense> getExpenseByUserId(Long userId) {
        return expenseRepository.findByUserId(userId);
    }


}
