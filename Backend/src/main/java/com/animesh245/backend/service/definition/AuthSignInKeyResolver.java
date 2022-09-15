package com.animesh245.backend.service.definition;

import io.jsonwebtoken.SigningKeyResolver;

import javax.crypto.SecretKey;

public interface AuthSignInKeyResolver extends SigningKeyResolver
{
    SecretKey getSecretKey();
}
