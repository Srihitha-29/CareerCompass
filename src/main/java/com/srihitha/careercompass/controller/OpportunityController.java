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

    @PostMapping
    public ResponseEntity<OpportunityResponse> createOpportunity(
            @Valid @RequestBody OpportunityRequest request) {
        return ResponseEntity.ok(opportunityService.createOpportunity(request));
    }

    @GetMapping
    public ResponseEntity<List<OpportunityResponse>> getMyOpportunities() {
        return ResponseEntity.ok(opportunityService.getMyOpportunities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OpportunityResponse> getOpportunityById(@PathVariable Long id) {
        return ResponseEntity.ok(opportunityService.getOpportunityById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OpportunityResponse> updateOpportunity(
            @PathVariable Long id,
            @Valid @RequestBody OpportunityRequest request) {
        return ResponseEntity.ok(opportunityService.updateOpportunity(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOpportunity(@PathVariable Long id) {
        opportunityService.deleteOpportunity(id);
        return ResponseEntity.ok("Opportunity Deleted Successfully");
    }
}