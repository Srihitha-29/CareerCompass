package com.srihitha.careercompass.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ResumeResponse {

    private Long id;
    private String title;
    private String link;
    private LocalDate uploadedDate;
}