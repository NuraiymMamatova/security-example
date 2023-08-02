package com.example.todo.services.servicesimpl;

import com.example.todo.model.dto.SignupRequest;
import com.example.todo.model.dto.SignupResponse;
import com.example.todo.model.entities.User;
import com.example.todo.repositories.UserRepository;
import com.example.todo.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public SignupResponse createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        User createdUser = userRepository.save(user);
        SignupResponse signupResponse = new SignupResponse();
        signupResponse.setId(createdUser.getId());
        signupResponse.setEmail(createdUser.getEmail());
        signupResponse.setName(createdUser.getName());
        return signupResponse;
    }
}
