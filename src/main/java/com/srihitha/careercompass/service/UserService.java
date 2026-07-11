package com.srihitha.careercompass.service;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.srihitha.careercompass.dto.LoginRequest;
import com.srihitha.careercompass.dto.LoginResponse;
import com.srihitha.careercompass.dto.RegisterRequest;
import com.srihitha.careercompass.entity.User;
import com.srihitha.careercompass.exception.EmailAlreadyExistsException;
import com.srihitha.careercompass.exception.InvalidCredentialsException;
import com.srihitha.careercompass.repository.UserRepository;
import com.srihitha.careercompass.util.JwtUtil;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil=jwtUtil;
    }
    // Registration Logic
    public void registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
       user.setPassword(
    passwordEncoder.encode(request.getPassword())
);
       userRepository.save(user);
    }
    // Login Logic
    public LoginResponse loginUser(LoginRequest request) {
        Optional<User> userOptional =
                userRepository.findByEmail(request.getEmail());

        if(userOptional.isEmpty()) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        User user = userOptional.get();

        if(!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new LoginResponse(token, user.getFullName());
    }
}