package com.example.todo.services.auth;

import com.example.todo.dto.SignupDTO;
import com.example.todo.dto.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupDTO signupDTO);
}
