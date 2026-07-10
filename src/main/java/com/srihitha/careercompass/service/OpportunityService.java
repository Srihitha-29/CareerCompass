package com.srihitha.careercompass.service;

import java.util.List;

import com.srihitha.careercompass.dto.OpportunityRequest;
import com.srihitha.careercompass.dto.OpportunityResponse;

public interface OpportunityService {

    // Create Opportunity
    OpportunityResponse createOpportunity(
            OpportunityRequest request,
            String token
    );

    // Get All Opportunities of Logged-in User
    List<OpportunityResponse> getMyOpportunities(String token);

    // Get Single Opportunity
    OpportunityResponse getOpportunityById(
            Long id,
            String token
    );

    // Update Opportunity
    OpportunityResponse updateOpportunity(
            Long id,
            OpportunityRequest request,
            String token
    );

    // Delete Opportunity
    void deleteOpportunity(
            Long id,
            String token
    );
}