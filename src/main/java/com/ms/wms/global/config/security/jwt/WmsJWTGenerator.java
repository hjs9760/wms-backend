package com.ms.wms.global.config.security.jwt;

import com.auth0.jwt.interfaces.Claim;
import com.ms.wms.domain.member.domain.Member;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WmsJWTGenerator {
    public static final String SECRET_KEY = "wms-secret-key-20200420";    // 서버에서 토큰을 생성하기위한 암호코드
    public static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 30L; // 30days  토큰만료시한

    public static String generate(Member member) {
        return JWTUtils.createToken(createClaim(member), createExpiresAt(), SECRET_KEY);
    }

    public static Map<String, Claim> decode(String token) {
        if (!token.startsWith("Bearer "))
            throw new RuntimeException("토큰 타입이 잘못되었습니다.");

        token = token.replace("Bearer ", "");
        return JWTUtils.decode(token, SECRET_KEY);
    }

    private static Date createExpiresAt() {
        return new Date(System.currentTimeMillis() + EXPIRATION_TIME);
    }

    private static Map<String, String> createClaim(Member member) {
        Map<String, String> claimMap = new HashMap<>();
        claimMap.put("id", member.getId().toString());
        return claimMap;
    }

    public static String generate(Long dbPK) {
        return JWTUtils.createToken(createClaim(dbPK), createExpiresAt(), SECRET_KEY);
    }

    private static Map<String, String> createClaim(Long oauth2UserId) {
        Map<String, String> claimMap = new HashMap<>();
        claimMap.put("id", oauth2UserId.toString());
        return claimMap;
    }
}