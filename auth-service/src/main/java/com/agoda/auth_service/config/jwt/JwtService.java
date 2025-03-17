package com.agoda.auth_service.config.jwt;

import com.agoda.auth_service.dto.response.UserDto;
import com.agoda.auth_service.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private String secretkey = "";

    @Value("${auth.accessToken.expirationInMils}")
    private int expirationTimeAccessToken;

    @Value("${auth.refreshToken.expirationInMils}")
    private int expirationTimeRefreshToken;

    public JwtService() {

        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secretkey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public String generateAccessToken(String refreshToken) {

        try{
            Claims claims = extractAllClaims(refreshToken);
            HashMap<String, Object> newClaims = new HashMap<>();
            newClaims.put("authorities", claims.get("authorities"));
            newClaims.put("token_type", "access_token");

            return Jwts.builder()
                    .id(claims.getId())
                    .claims(newClaims)
                    .subject(claims.getSubject())
                    .issuer(claims.getIssuer())
                    .signWith(getKey())
                    .issuedAt(new Date())
                    .expiration(new Date((new Date()).getTime() +expirationTimeAccessToken))
                    .compact();

        } catch(JwtException e){
            throw new JwtException(e.getMessage());
        }
    }

    public String generateRefreshToken(User userDetails){

        try{
            HashMap<String,Object> authorities = new HashMap<>();
            authorities.put("authorities",userDetails.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()));

            HashMap<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("token_type", "refresh_token");

            return Jwts.builder()
                    .id(UUID.randomUUID().toString())
                    .claims(extraClaims)
                    .claim("authorities",authorities)
                    .subject(userDetails.getUsername())
                    .issuer(userDetails.getAuthorities().iterator().next().getAuthority())
                    .signWith(getKey())
                    .issuedAt(new Date())
                    .expiration(new Date((new Date()).getTime() + expirationTimeRefreshToken))
                    .compact();
        } catch (JwtException e){
            throw new JwtException(e.getMessage());
        }
    }

    public boolean isTokenValid(String token) {
        try {
            final String username = extractUsername(token);
            return !isTokenExpired(token);
        }catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                IllegalArgumentException e) {
            throw new JwtException(e.getMessage());
        }
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractEmail(String token){
        return extractAllClaims(token).getSubject();
    }

    public String extractId(String token) {
        return extractAllClaims(token).getId();
    }
}
