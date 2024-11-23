package com.project.lms.ExpenseTrackerLms.services.income;

import com.project.lms.ExpenseTrackerLms.dto.ExpenseDTO;
import com.project.lms.ExpenseTrackerLms.dto.IncomeDTO;
import com.project.lms.ExpenseTrackerLms.entity.Expense;
import com.project.lms.ExpenseTrackerLms.entity.Income;
import com.project.lms.ExpenseTrackerLms.repository.IncomeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class IncomeServiceImpl implements IncomeService{

    private final IncomeRepository incomeRepository;

    public Income postIncome(IncomeDTO incomeDTO){
        return saveOrUpdateIncome(new Income(), incomeDTO);
    }

    private Income saveOrUpdateIncome(Income income, IncomeDTO incomeDTO){
        income.setTitle(incomeDTO.getTitle());
        income.setDate(ObjectUtils.isEmpty(incomeDTO.getDate()) ? LocalDate.now() :incomeDTO.getDate());
        income.setAmount(ObjectUtils.isEmpty(incomeDTO.getAmount()) ? 0 :incomeDTO.getAmount());
        income.setCategory(incomeDTO.getCategory());
        income.setDescription(incomeDTO.getDescription());
        income.setUserId(incomeDTO.getUserId());

        return incomeRepository.save(income);
    }

    public Income updateIncome(IncomeDTO incomeDTO){
        Optional<Income> optionalIncome = incomeRepository.findById(incomeDTO.getIncomeId());
        if(optionalIncome.isPresent()){
            return saveOrUpdateIncome(optionalIncome.get(), incomeDTO);
        }else{
            throw new EntityNotFoundException("Expense not found with id " + incomeDTO.getIncomeId() + " for userId " + incomeDTO.getUserId());
        }
    }

    public List<IncomeDTO> getAllIncomes(){
        return incomeRepository.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
                .map(Income::getIncomeDto)
                .collect(Collectors.toList());
    }
    public  IncomeDTO getIncomeById(Long id){
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if(optionalIncome.isPresent()){
            return optionalIncome.get().getIncomeDto();
        }else{
            throw new EntityNotFoundException("Income is not present with id " + id);
        }
    }
    public void deleteIncome(Long id){
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if (optionalIncome.isPresent()){
            incomeRepository.deleteById(id);
        } else{
            throw new EntityNotFoundException("Expense is not present with id" + id);
        }
    }

//    @Override
//    public List<Income> getIncomeByUserId(Long userId) {
//        List<Income> incomeList = incomeRepository.findByUserId(userId);
//        return incomeList;
//    }

    @Override
    public List<Income> getIncomeByUserId(Long userId) {
        List<Income> incomes = incomeRepository.findByUserId(userId);

        if (incomes.isEmpty()) {
            throw new EntityNotFoundException("No income records found for user with ID: " + userId);
        }

        return incomes;
    }

    @Override
    public void deleteAllByUserId(Long userId) {
        List<Income> incomes = incomeRepository.findByUserId(userId);
        if (incomes.isEmpty()) {
            throw new EntityNotFoundException("No income records found for user with ID: " + userId);
        }
        incomeRepository.deleteAll(incomes);  // Deletes all incomes for the user
    }

}
