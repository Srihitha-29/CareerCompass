package com.srihitha.careercompass.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.srihitha.careercompass.dto.ResumeRequest;
import com.srihitha.careercompass.dto.ResumeResponse;
import com.srihitha.careercompass.service.ResumeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping
    public ResponseEntity<ResumeResponse> createResume(
            @Valid @RequestBody ResumeRequest request) {
        return ResponseEntity.ok(resumeService.createResume(request));
    }

    @GetMapping
    public ResponseEntity<List<ResumeResponse>> getMyResumes() {
        return ResponseEntity.ok(resumeService.getMyResumes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResumeResponse> updateResume(
            @PathVariable Long id,
            @Valid @RequestBody ResumeRequest request) {
        return ResponseEntity.ok(resumeService.updateResume(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResume(@PathVariable Long id) {
        resumeService.deleteResume(id);
        return ResponseEntity.ok("Resume Deleted Successfully");
    }
}