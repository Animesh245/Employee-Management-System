package com.animesh245.backend.service.definition;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface AuthService
{
    String extractUsername(String token);
    Date extractExpiration(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    Claims extractAllClaims(String token);

    Boolean isTokenExpired(String token);

//    void generateToken(Employee employee);

    String generateToken(UserDetails userDetails);

    String createToken(Map<String , Object> claims, String subject);

    Boolean validateToken(String token, UserDetails userDetails);

}
