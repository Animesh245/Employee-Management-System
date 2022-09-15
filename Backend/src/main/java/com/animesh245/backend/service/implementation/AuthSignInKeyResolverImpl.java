package com.animesh245.backend.service.implementation;

import com.animesh245.backend.service.definition.AuthSignInKeyResolver;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;

@Service
public class AuthSignInKeyResolverImpl implements AuthSignInKeyResolver
{


    @Value("${jwt.secret.key}")
    String secretKeyString;


    private SecretKey secretKey;

    @Override
    public SecretKey getSecretKey()
    {


        if(secretKey == null)
        {
            this.secretKey = Keys.hmacShaKeyFor(Base64.getEncoder().encode(this.secretKeyString.getBytes()));
        }
        return this.secretKey;
    }

    @Override
    public Key resolveSigningKey(JwsHeader jwsHeader, Claims claims) {
        return null; // this.getSecretKey();
    }

    @Override
    public Key resolveSigningKey(JwsHeader jwsHeader, String s) {
        return null; //this.getSecretKey();
    }
}
