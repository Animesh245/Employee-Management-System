package com.animesh245.backend.service.implementation;

import com.animesh245.backend.service.definition.AuthService;
import com.animesh245.backend.service.definition.AuthSignInKeyResolver;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class AuthServiceImpl implements AuthService, Serializable
{
    private static final long serialVersionUID = -2550185165626007488L;
    private final AuthSignInKeyResolver authSignInKeyResolver;

    public AuthServiceImpl(AuthSignInKeyResolver authSignInKeyResolver) {
        this.authSignInKeyResolver = authSignInKeyResolver;
    }

    public String extractUsername(String token)
    {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKeyResolver(authSignInKeyResolver)
                .build().parseClaimsJwt(token).getBody();
//                parser().setSigningKey(getSigningKey()).parseClaimsJwt(token).getBody();
    }

    public Boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails)
    {
        Map<String , Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

//    public void generateToken(Employee employee)
//    {
//        //Replace token with JWT
//        var claims = new HashMap<String , Object>();
//        claims.put("iss", employee.getId());
//        claims.put("exp", System.currentTimeMillis() + 1000 * 60 * 60 * 12);
//        claims.put("iat", System.currentTimeMillis());
//        claims.put("sub", employee.getEmail());
//        claims.put("aud", employee.getAuthorities().toString());
//    }

    public String createToken(Map<String, Object> claims, String subject)
    {
        return Jwts.builder()
                .setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) //10 hours from now
                .signWith(authSignInKeyResolver.getSecretKey(), SignatureAlgorithm.HS256).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails)
    {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && Boolean.FALSE.equals(isTokenExpired(token)));
    }
}
