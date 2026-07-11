package com.srihitha.careercompass.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.srihitha.careercompass.dto.ResumeRequest;
import com.srihitha.careercompass.dto.ResumeResponse;
import com.srihitha.careercompass.entity.Resume;
import com.srihitha.careercompass.entity.User;
import com.srihitha.careercompass.exception.AccessDeniedException;
import com.srihitha.careercompass.exception.ResourceNotFoundException;
import com.srihitha.careercompass.repository.ResumeRepository;
import com.srihitha.careercompass.repository.UserRepository;
import com.srihitha.careercompass.service.ResumeService;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    public ResumeServiceImpl(
            ResumeRepository resumeRepository,
            UserRepository userRepository) {

        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;
    }

    private User getLoggedInUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private ResumeResponse mapToResponse(Resume resume) {
        ResumeResponse response = new ResumeResponse();
        response.setId(resume.getId());
        response.setTitle(resume.getTitle());
        response.setLink(resume.getLink());
        response.setUploadedDate(resume.getUploadedDate());
        return response;
    }

    @Override
    public ResumeResponse createResume(ResumeRequest request) {
        User user = getLoggedInUser();

        Resume resume = new Resume();
        resume.setTitle(request.getTitle());
        resume.setLink(request.getLink());
        resume.setUploadedDate(LocalDate.now());
        resume.setUser(user);

        Resume saved = resumeRepository.save(resume);
        return mapToResponse(saved);
    }

    @Override
    public List<ResumeResponse> getMyResumes() {
        User user = getLoggedInUser();
        List<Resume> resumes = resumeRepository.findByUser(user);

        List<ResumeResponse> responses = new ArrayList<>();
        for (Resume resume : resumes) {
            responses.add(mapToResponse(resume));
        }
        return responses;
    }

    @Override
    public ResumeResponse updateResume(Long id, ResumeRequest request) {
        User user = getLoggedInUser();

        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resume not found"));

        if (!resume.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Access Denied");
        }

        resume.setTitle(request.getTitle());
        resume.setLink(request.getLink());

        Resume updated = resumeRepository.save(resume);
        return mapToResponse(updated);
    }

    @Override
    public void deleteResume(Long id) {
        User user = getLoggedInUser();

        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resume not found"));

        if (!resume.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Access Denied");
        }

        resumeRepository.delete(resume);
    }
}