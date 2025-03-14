package com.agoda.auth_service.service;

import com.agoda.auth_service.config.jwt.JwtService;
import com.agoda.auth_service.dto.request.AuthenticationRequest;
import com.agoda.auth_service.dto.response.JwtResponse;
import com.agoda.auth_service.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenService tokenService;
    private final String BLACKLISTED_TOKEN = "blacklisted_token:";
    private final CustomUserDetailsService userDetailsService;

    public JwtResponse login(AuthenticationRequest request){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String refreshToken = jwtService.generateRefreshToken(user);
        String accessToken = jwtService.generateAccessToken(refreshToken);
        return new JwtResponse(accessToken,refreshToken);
    }

    public Boolean checkToken(String token){
        return jwtService.isTokenValid(token) && !isTokenBlacklisted(token);
    }


    public JwtResponse refresh(String refreshToken){

        String new_access_token = "";
        try{
            String email = jwtService.extractEmail(refreshToken);

            User user = (User)userDetailsService.loadUserByUsername(email);

            if(isTokenBlacklisted(refreshToken)) {

                new_access_token = jwtService.generateAccessToken(refreshToken);
            }
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
        }
        return new JwtResponse(new_access_token, refreshToken);
    }

    public void logout(String accessToken) {
        saveTokenIdToRedis(accessToken);
    }

    private void saveTokenIdToRedis(String token){
        Long refresh_token_TTL = jwtService.extractExpiration(token).getTime();
        String idToken = jwtService.extractId(token);
        tokenService.saveToken(BLACKLISTED_TOKEN + idToken, "", refresh_token_TTL);
    }

    private boolean isTokenBlacklisted(String refresh_token) {
        String idToken = jwtService.extractId(refresh_token);
        String key = BLACKLISTED_TOKEN + idToken;
        return !(tokenService.getToken(key) == null);
    }

}
