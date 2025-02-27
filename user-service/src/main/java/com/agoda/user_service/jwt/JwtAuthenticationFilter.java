package com.agoda.user_service.jwt;

import com.agoda.user_service.client.AuthServiceClient;
import com.agoda.user_service.dto.request.ValidateToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Jwts;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import org.json.JSONObject;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthServiceClient authServiceClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException
    {
        String jwt = parseJwt(request);
        try {
            if (StringUtils.hasText(jwt) && authServiceClient.validateToken(new ValidateToken(jwt)).getStatusCode().is2xxSuccessful()) {
                String[] parts = jwt.split("\\.");
                String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));
                JSONObject payload = new JSONObject(payloadJson);
                String issuer = payload.optString("iss", "Unknown");
                String sub = payload.optString("sub", "Unknown");

                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(issuer);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        sub, null, Collections.singleton(authority));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        filterChain.doFilter(request, response);

    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return  null;
    }
}
