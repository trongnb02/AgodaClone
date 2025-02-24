package com.agoda.auth_service.service;

import com.agoda.auth_service.client.UserServiceClient;
import com.agoda.auth_service.dto.response.ApiResponse;
import com.agoda.auth_service.dto.response.UserDto;
import com.agoda.auth_service.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserServiceClient userServiceClient;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String gmail) throws UsernameNotFoundException {

        ResponseEntity<ApiResponse> responseEntity = userServiceClient.getUserByEmail(gmail);
        UserDto user;
        if (responseEntity != null && responseEntity.getBody() != null && responseEntity.getBody().getData() != null) {
            user = modelMapper.map(responseEntity.getBody().getData(), UserDto.class);
        } else {
            throw new UsernameNotFoundException("User Not Found");
        }

        return new User(user);
    }
}
