package com.ms.wms.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Map;

public class JWTUtils {

    public static String createToken(Map<String, String> claimMap, Date expiresAt, String secretKey) {
        JWTCreator.Builder builder = JWT.create();
        for (Map.Entry<String, String> claim : claimMap.entrySet()) {
            String name = claim.getKey();
            String value = claim.getValue();
            builder.withClaim(name, value);
        }

        String token = builder
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC512(secretKey));

        return token;
    }

    public static Map<String, Claim> decode(String token, String secretKey) {
        DecodedJWT jwtVerifier = JWT.require(Algorithm.HMAC512(secretKey.getBytes()))
                .build()
                .verify(token);

        return jwtVerifier.getClaims();
    }
}