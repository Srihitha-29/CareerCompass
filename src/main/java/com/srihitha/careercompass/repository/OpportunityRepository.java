package com.srihitha.careercompass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srihitha.careercompass.entity.Opportunity;
import com.srihitha.careercompass.entity.User;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    List<Opportunity> findByUser(User user);

}