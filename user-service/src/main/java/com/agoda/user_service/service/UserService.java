package com.agoda.user_service.service;

import com.agoda.user_service.dto.request.RegisterRequest;
import com.agoda.user_service.dto.request.UpdateUserRequest;
import com.agoda.user_service.exception.AlreadyExistsException;
import com.agoda.user_service.exception.EmailNotFoundException;
import com.agoda.user_service.exception.UserNotFoundException;
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
                    user.setRole(Role.CUSTOMER);
                    user.setActive(Active.ACTIVE);
                    return  userRepository.save(user);
                }) .orElseThrow(() -> new AlreadyExistsException("Oops! user already exists!"));
    }

    protected User findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    protected User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("User not found!"));
    }

    public User getUserById(String id) {
        return findUserById(id);
    }

    public User getUserByEmail(String email) {
        return findUserByEmail(email);
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

    public void deleteUserByEmail(String email) {
        User user = findUserByEmail(email);
        userRepository.delete(user);
    }

}
