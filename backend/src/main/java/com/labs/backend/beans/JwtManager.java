package com.labs.backend.beans;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.ejb.Stateless;

@Stateless
public class JwtManager {

    public String createTokenByLogin(String login) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return JWT.create()
                    .withClaim("login", login)
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Can't create token: " + exception.getMessage());
        }
    }

    public DecodedJWT decodeToken(String token) {
        try {
            return JWT.decode(token);
        } catch (JWTDecodeException exception) {
            return null;
        }
    }
}