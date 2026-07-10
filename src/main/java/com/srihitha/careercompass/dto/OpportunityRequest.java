package com.srihitha.careercompass.dto;

import java.time.LocalDate;

import com.srihitha.careercompass.enums.Category;
import com.srihitha.careercompass.enums.Status;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OpportunityRequest {

   @NotBlank(message = "Title is required")
private String title;

@NotBlank(message = "Organization is required")
private String organization;

    private Category category;

    private Status status;

    private String notes;

    private LocalDate appliedDate;

    private LocalDate deadline;

    private String resumeName;
}