package com.srihitha.careercompass.service;

import java.util.List;

import com.srihitha.careercompass.dto.OpportunityRequest;
import com.srihitha.careercompass.dto.OpportunityResponse;

public interface OpportunityService {

    OpportunityResponse createOpportunity(OpportunityRequest request);

    List<OpportunityResponse> getMyOpportunities();

    OpportunityResponse getOpportunityById(Long id);

    OpportunityResponse updateOpportunity(Long id, OpportunityRequest request);

    void deleteOpportunity(Long id);
}