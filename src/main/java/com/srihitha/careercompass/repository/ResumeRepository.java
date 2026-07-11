package com.srihitha.careercompass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srihitha.careercompass.entity.Resume;
import com.srihitha.careercompass.entity.User;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    List<Resume> findByUser(User user);

}