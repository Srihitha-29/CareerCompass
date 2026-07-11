package com.srihitha.careercompass.service;

import java.util.List;

import com.srihitha.careercompass.dto.ResumeRequest;
import com.srihitha.careercompass.dto.ResumeResponse;

public interface ResumeService {

    ResumeResponse createResume(ResumeRequest request);

    List<ResumeResponse> getMyResumes();

    ResumeResponse updateResume(Long id, ResumeRequest request);

    void deleteResume(Long id);
}