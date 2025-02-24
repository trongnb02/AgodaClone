package com.agoda.user_service.service;

import com.agoda.user_service.dto.request.RegisterRequest;
import com.agoda.user_service.dto.request.UpdateUserRequest;
import com.agoda.user_service.exception.AlreadyExistsException;
import com.agoda.user_service.exception.EmailNotFoundException;
import com.agoda.user_service.exception.UserNotFoundException;
import com.agoda.user_service.exception.UsernameNotFoundException;
import com.agoda.user_service.model.User;
import com.agoda.user_service.model.UserDetails;
import com.agoda.user_service.model.enums.Active;
import com.agoda.user_service.model.enums.Role;
import com.agoda.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public User register(RegisterRequest request) {
        return  Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    User user = new User();
                    user.setEmail(request.getEmail());
                    user.setPassword(passwordEncoder.encode(request.getPassword()));
                    user.setUsername(request.getUsername());
                    user.setRole(Role.CUSTOMER);
                    user.setActive(Active.ACTIVE);
                    return  userRepository.save(user);
                }) .orElseThrow(() -> new AlreadyExistsException("Oops!" +request.getEmail() +" already exists!"));
    }

    protected User findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Username not found!"));
    }

    protected User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("User not found"));
    }

    protected User findUserByUsername(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

    public User getUserById(String id) {
        return findUserById(id);
    }

    public User getUserByEmail(String email) {
        return findUserByEmail(email);
    }

    public User getUserByUsername(String username) {
        return findUserByUsername(username);
    }

    private UserDetails updateUserDetails(UserDetails toUpdate, UserDetails request) {
        toUpdate = toUpdate == null ? new UserDetails() : toUpdate;
        modelMapper.map(request, toUpdate);
        return toUpdate;
    }

    public User updateUserByEmail(UpdateUserRequest request) {
        User toUpdate = findUserByEmail(request.getEmail());

        request.setUserDetails(updateUserDetails(toUpdate.getUserDetails(), request.getUserDetails()));
        modelMapper.map(request, toUpdate);

        return userRepository.save(toUpdate);
    }

}
