package com.example.todo.services.servicesimpl;

import com.example.todo.model.dto.SignupRequest;
import com.example.todo.model.dto.SignupResponse;
import com.example.todo.model.entities.User;
import com.example.todo.repositories.UserRepository;
import com.example.todo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Logic to get the user from the DB
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
