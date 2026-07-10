package com.srihitha.careercompass.dto;

import java.time.LocalDate;

import com.srihitha.careercompass.enums.Category;
import com.srihitha.careercompass.enums.Status;

import lombok.Data;

@Data
public class OpportunityResponse {

    private Long id;

    private String title;

    private String organization;

    private Category category;

    private Status status;

    private String notes;

    private LocalDate appliedDate;

    private LocalDate deadline;

    private String resumeName;
}