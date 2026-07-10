package com.srihitha.careercompass.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.srihitha.careercompass.dto.OpportunityRequest;
import com.srihitha.careercompass.dto.OpportunityResponse;
import com.srihitha.careercompass.service.OpportunityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/opportunities")
public class OpportunityController {

    private final OpportunityService opportunityService;

    public OpportunityController(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<OpportunityResponse> createOpportunity(
            @Valid @RequestBody OpportunityRequest request,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(
                opportunityService.createOpportunity(request, token));
    }

    // ================= GET ALL =================
    @GetMapping
    public ResponseEntity<List<OpportunityResponse>> getMyOpportunities(
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(
                opportunityService.getMyOpportunities(token));
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<OpportunityResponse> getOpportunityById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(
                opportunityService.getOpportunityById(id, token));
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ResponseEntity<OpportunityResponse> updateOpportunity(
            @PathVariable Long id,
            @Valid @RequestBody OpportunityRequest request,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(
                opportunityService.updateOpportunity(id, request, token));
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOpportunity(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token) {

        opportunityService.deleteOpportunity(id, token);

        return ResponseEntity.ok("Opportunity Deleted Successfully");
    }
}