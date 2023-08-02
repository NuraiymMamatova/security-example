package com.example.todo.services;

import com.example.todo.model.dto.SignupRequest;
import com.example.todo.model.dto.SignupResponse;

public interface AuthService {
    SignupResponse createUser(SignupRequest signupRequest);
}
