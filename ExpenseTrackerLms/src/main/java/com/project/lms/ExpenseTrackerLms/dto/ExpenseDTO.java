package com.project.lms.ExpenseTrackerLms.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseDTO {

    private Long expenseId;

    private String title;

    private String description;

    private String category;

    private LocalDate date;

    private Integer amount;

    private Long userId;

}
