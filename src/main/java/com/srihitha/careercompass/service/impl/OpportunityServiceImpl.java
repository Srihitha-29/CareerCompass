package com.srihitha.careercompass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.srihitha.careercompass.dto.OpportunityRequest;
import com.srihitha.careercompass.dto.OpportunityResponse;
import com.srihitha.careercompass.entity.Opportunity;
import com.srihitha.careercompass.entity.User;
import com.srihitha.careercompass.exception.AccessDeniedException;
import com.srihitha.careercompass.exception.ResourceNotFoundException;
import com.srihitha.careercompass.repository.OpportunityRepository;
import com.srihitha.careercompass.repository.UserRepository;
import com.srihitha.careercompass.service.OpportunityService;

@Service
public class OpportunityServiceImpl implements OpportunityService {

    private final OpportunityRepository opportunityRepository;
    private final UserRepository userRepository;

    public OpportunityServiceImpl(
            OpportunityRepository opportunityRepository,
            UserRepository userRepository) {

        this.opportunityRepository = opportunityRepository;
        this.userRepository = userRepository;
    }

    // ================= COMMON METHOD =================

    private User getLoggedInUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private OpportunityResponse mapToResponse(Opportunity opportunity) {
        OpportunityResponse response = new OpportunityResponse();
        response.setId(opportunity.getId());
        response.setTitle(opportunity.getTitle());
        response.setOrganization(opportunity.getOrganization());
        response.setCategory(opportunity.getCategory());
        response.setStatus(opportunity.getStatus());
        response.setNotes(opportunity.getNotes());
        response.setAppliedDate(opportunity.getAppliedDate());
        response.setDeadline(opportunity.getDeadline());
        response.setResumeName(opportunity.getResumeName());
        return response;
    }

    // ================= CREATE =================

    @Override
    public OpportunityResponse createOpportunity(OpportunityRequest request) {
        User user = getLoggedInUser();

        Opportunity opportunity = new Opportunity();
        opportunity.setTitle(request.getTitle());
        opportunity.setOrganization(request.getOrganization());
        opportunity.setCategory(request.getCategory());
        opportunity.setStatus(request.getStatus());
        opportunity.setNotes(request.getNotes());
        opportunity.setAppliedDate(request.getAppliedDate());
        opportunity.setDeadline(request.getDeadline());
        opportunity.setResumeName(request.getResumeName());
        opportunity.setUser(user);

        Opportunity savedOpportunity = opportunityRepository.save(opportunity);
        return mapToResponse(savedOpportunity);
    }

    // ================= GET ALL =================

    @Override
    public List<OpportunityResponse> getMyOpportunities() {
        User user = getLoggedInUser();
        List<Opportunity> opportunities = opportunityRepository.findByUser(user);

        List<OpportunityResponse> responses = new ArrayList<>();
        for (Opportunity opportunity : opportunities) {
            responses.add(mapToResponse(opportunity));
        }
        return responses;
    }

    // ================= GET BY ID =================

    @Override
    public OpportunityResponse getOpportunityById(Long id) {
        User user = getLoggedInUser();

        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Opportunity not found"));

        if (!opportunity.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Access Denied");
        }

        return mapToResponse(opportunity);
    }

    // ================= UPDATE =================

    @Override
    public OpportunityResponse updateOpportunity(Long id, OpportunityRequest request) {
        User user = getLoggedInUser();

        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Opportunity not found"));

        if (!opportunity.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Access Denied");
        }

        opportunity.setTitle(request.getTitle());
        opportunity.setOrganization(request.getOrganization());
        opportunity.setCategory(request.getCategory());
        opportunity.setStatus(request.getStatus());
        opportunity.setNotes(request.getNotes());
        opportunity.setAppliedDate(request.getAppliedDate());
        opportunity.setDeadline(request.getDeadline());
        opportunity.setResumeName(request.getResumeName());

        Opportunity updatedOpportunity = opportunityRepository.save(opportunity);
        return mapToResponse(updatedOpportunity);
    }

    // ================= DELETE =================

    @Override
    public void deleteOpportunity(Long id) {
        User user = getLoggedInUser();

        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Opportunity not found"));

        if (!opportunity.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Access Denied");
        }

        opportunityRepository.delete(opportunity);
    }
}