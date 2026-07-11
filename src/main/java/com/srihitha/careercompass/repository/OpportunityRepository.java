package com.srihitha.careercompass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srihitha.careercompass.entity.Opportunity;
import com.srihitha.careercompass.entity.User;
import com.srihitha.careercompass.enums.Category;
import com.srihitha.careercompass.enums.Status;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    List<Opportunity> findByUser(User user);

    long countByUser(User user);

    long countByUserAndStatus(User user, Status status);

    long countByUserAndCategory(User user, Category category);

}