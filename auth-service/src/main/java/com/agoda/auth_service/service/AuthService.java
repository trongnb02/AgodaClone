package com.agoda.auth_service.service;

import com.agoda.auth_service.config.jwt.JwtService;
import com.agoda.auth_service.dto.request.AuthenticationRequest;
import com.agoda.auth_service.dto.response.JwtResponse;
import com.agoda.auth_service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public JwtResponse login(AuthenticationRequest request){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateToken((User) authentication.getPrincipal());
        return new JwtResponse(jwt);
    }

    public Boolean checkToken(String token){
        return jwtService.isTokenValid(token);
    }

}
