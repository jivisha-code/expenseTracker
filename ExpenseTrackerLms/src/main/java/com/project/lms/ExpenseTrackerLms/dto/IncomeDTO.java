package com.project.lms.ExpenseTrackerLms.dto;

import lombok.Data;

import java.time.LocalDate;

@Data

public class IncomeDTO {
    private Long incomeId;

    private String title;

    private Integer amount;

    private LocalDate date;

    private String category;

    private String description;

    private Long userId;
}
