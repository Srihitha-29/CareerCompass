package com.srihitha.careercompass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.srihitha.careercompass.dto.OpportunityRequest;
import com.srihitha.careercompass.dto.OpportunityResponse;
import com.srihitha.careercompass.entity.Opportunity;
import com.srihitha.careercompass.entity.User;
import com.srihitha.careercompass.repository.OpportunityRepository;
import com.srihitha.careercompass.repository.UserRepository;
import com.srihitha.careercompass.service.OpportunityService;
import com.srihitha.careercompass.util.JwtUtil;

@Service
public class OpportunityServiceImpl implements OpportunityService {

    private final OpportunityRepository opportunityRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public OpportunityServiceImpl(
            OpportunityRepository opportunityRepository,
            UserRepository userRepository,
            JwtUtil jwtUtil) {

        this.opportunityRepository = opportunityRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // ================= COMMON METHOD =================

    private User getLoggedInUser(String token) {

        token = token.substring(7);

        String email = jwtUtil.extractEmail(token);

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
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
    public OpportunityResponse createOpportunity(
            OpportunityRequest request,
            String token) {

        User user = getLoggedInUser(token);

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

        Opportunity savedOpportunity =
                opportunityRepository.save(opportunity);

        return mapToResponse(savedOpportunity);
    }

    // ================= GET ALL =================

    @Override
    public List<OpportunityResponse> getMyOpportunities(String token) {

        User user = getLoggedInUser(token);

        List<Opportunity> opportunities =
                opportunityRepository.findByUser(user);

        List<OpportunityResponse> responses =
                new ArrayList<>();

        for (Opportunity opportunity : opportunities) {
            responses.add(mapToResponse(opportunity));
        }

        return responses;
    }

    // ================= GET BY ID =================

    @Override
    public OpportunityResponse getOpportunityById(
            Long id,
            String token) {

        User user = getLoggedInUser(token);

        Opportunity opportunity =
                opportunityRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Opportunity not found"));

        if (!opportunity.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access Denied");
        }

        return mapToResponse(opportunity);
    }

    // ================= UPDATE =================

    @Override
    public OpportunityResponse updateOpportunity(
            Long id,
            OpportunityRequest request,
            String token) {

        User user = getLoggedInUser(token);

        Opportunity opportunity =
                opportunityRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Opportunity not found"));

        if (!opportunity.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access Denied");
        }

        opportunity.setTitle(request.getTitle());
        opportunity.setOrganization(request.getOrganization());
        opportunity.setCategory(request.getCategory());
        opportunity.setStatus(request.getStatus());
        opportunity.setNotes(request.getNotes());
        opportunity.setAppliedDate(request.getAppliedDate());
        opportunity.setDeadline(request.getDeadline());
        opportunity.setResumeName(request.getResumeName());

        Opportunity updatedOpportunity =
                opportunityRepository.save(opportunity);

        return mapToResponse(updatedOpportunity);
    }

    // ================= DELETE =================

    @Override
    public void deleteOpportunity(
            Long id,
            String token) {

        User user = getLoggedInUser(token);

        Opportunity opportunity =
                opportunityRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Opportunity not found"));

        if (!opportunity.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access Denied");
        }

        opportunityRepository.delete(opportunity);
    }
}