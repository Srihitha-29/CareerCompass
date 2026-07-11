package com.srihitha.careercompass.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResumeRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Link is required")
    private String link;
}