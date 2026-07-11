package com.srihitha.careercompass.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.srihitha.careercompass.dto.DashboardResponse;
import com.srihitha.careercompass.entity.User;
import com.srihitha.careercompass.enums.Category;
import com.srihitha.careercompass.enums.Status;
import com.srihitha.careercompass.exception.ResourceNotFoundException;
import com.srihitha.careercompass.repository.OpportunityRepository;
import com.srihitha.careercompass.repository.UserRepository;
import com.srihitha.careercompass.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final OpportunityRepository opportunityRepository;
    private final UserRepository userRepository;

    public DashboardServiceImpl(
            OpportunityRepository opportunityRepository,
            UserRepository userRepository) {

        this.opportunityRepository = opportunityRepository;
        this.userRepository = userRepository;
    }

    private User getLoggedInUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public DashboardResponse getDashboard() {

        User user = getLoggedInUser();

        DashboardResponse response = new DashboardResponse();

        response.setTotalOpportunities(
                opportunityRepository.countByUser(user));

        response.setApplied(
                opportunityRepository.countByUserAndStatus(user, Status.APPLIED));

        response.setPending(
                opportunityRepository.countByUserAndStatus(user, Status.PLANNED)
                + opportunityRepository.countByUserAndStatus(user, Status.REGISTERED)
                + opportunityRepository.countByUserAndStatus(user, Status.ONLINE_ASSESSMENT)
                + opportunityRepository.countByUserAndStatus(user, Status.INTERVIEW));

        response.setShortlisted(
                opportunityRepository.countByUserAndStatus(user, Status.SHORTLISTED));

        response.setSelected(
                opportunityRepository.countByUserAndStatus(user, Status.SELECTED));

        response.setRejected(
                opportunityRepository.countByUserAndStatus(user, Status.REJECTED));

        response.setInternships(
                opportunityRepository.countByUserAndCategory(user, Category.INTERNSHIP));

        response.setHackathons(
                opportunityRepository.countByUserAndCategory(user, Category.HACKATHON));

        return response;
    }
}