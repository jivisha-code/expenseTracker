package com.project.lms.ExpenseTrackerLms.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.lms.ExpenseTrackerLms.dto.IncomeDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incomeId;

    private String title;

    private Integer amount;

    private LocalDate date;

    private String category;

    private String description;

    private Long userId;

    public IncomeDTO getIncomeDto(){
        IncomeDTO incomeDTO = new IncomeDTO();

        incomeDTO.setIncomeId(incomeId);
        incomeDTO.setTitle(title);
        incomeDTO.setAmount(amount);
        incomeDTO.setCategory(category);
        incomeDTO.setDescription(description);
        incomeDTO.setDate(date);
        incomeDTO.setUserId(userId);

        return incomeDTO;
    }
}
