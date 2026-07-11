package com.srihitha.careercompass.dto;

import lombok.Data;

@Data
public class DashboardResponse {

    // Overall Opportunities
    private long totalOpportunities;

    // Status Counts
    private long applied;
    private long pending;
    private long shortlisted;
    private long selected;
    private long rejected;

    // Category Counts
    private long internships;
    private long hackathons;
}