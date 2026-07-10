package com.srihitha.careercompass.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srihitha.careercompass.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
boolean existsByEmail(String email); //checks whether the email exists.
Optional<User> findByEmail(String email); //need the user's details.
}
