package com.srihitha.careercompass.entity;

import java.time.LocalDate;
import lombok.Data;
import com.srihitha.careercompass.enums.Category;
import com.srihitha.careercompass.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "opportunities")
@Data
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String organization;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String notes;

    private LocalDate appliedDate;

    private LocalDate deadline;

    private String resumeName;

    private String link;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}