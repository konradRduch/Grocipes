package com.grocipes_backend.grocipes.security;

import com.grocipes_backend.grocipes.models.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import com.grocipes_backend.grocipes.repositories.UserEntityRepository;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JWTGenerator {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final UserEntityRepository userEntityRepository;

    public JWTGenerator(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public String generateToken(Authentication authentication){

        String username = authentication.getName();

        // Pobierz role użytkownika
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Integer userId = userEntityRepository.findByEmail(username)
                .map(UserEntity::getId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() +  + SecurityConstants.JWT_EXPIRATION);
        String token = Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .claim("roles", roles)  // Dodaj role do tokenu
                .setIssuedAt( new Date())
                .setExpiration(expireDate)
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    public String getUsernameFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();  // Zwróć sub, czyli użytkownika
    }

    public Integer getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return (Integer) claims.get("userId");  // Zwróć userId z claims
    }
    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was exprired or incorrect",ex.fillInStackTrace());
        }
    }



}
