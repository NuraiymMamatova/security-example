package com.example.todo.services;

import com.example.todo.model.dto.SignupRequest;
import com.example.todo.model.dto.SignupResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    SignupResponse createUser(SignupRequest signupRequest);

}
