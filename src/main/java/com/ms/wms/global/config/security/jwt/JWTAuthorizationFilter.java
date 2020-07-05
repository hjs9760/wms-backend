package com.ms.wms.global.config.security.jwt;

import com.auth0.jwt.interfaces.Claim;
import com.ms.wms.domain.member.domain.Role;
import com.ms.wms.global.config.exception.business.UnAuthorityException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    /**
     *
     * JWT 흐름
     *
     * 크게 2개가 있음
     *
     * 1. 로그인 후 JWT 발급하기 (oauth2로그인 후 successhandler에서)
     *
     * 2. API 요청시 JWT 함께 보내서, 해당 JWT의 유효성을 검증하는 과정 (필터에서)
     *
     *
     */

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String token = req.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
                throw new UnAuthorityException("No permission");
        }

        // 인증하기 위한 토큰(id, pw)
        UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
        // @AuthenticationPrincipal
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Map<String, Claim> claimMap = WmsJWTGenerator.decode(token); // payload 정보를 담아온다.

        Long id = Long.parseLong(claimMap.get("id").asString());
        String email = claimMap.get("email").asString();
        GrantedAuthority simpleGrantedAuthority;

        if(email.equals("jungsun9760@naver.com")) {
            simpleGrantedAuthority = new SimpleGrantedAuthority(Role.ROLE_ADMIN.getName());
        } else {
            simpleGrantedAuthority = new SimpleGrantedAuthority(Role.ROLE_USER.getName());
        }

        return new UsernamePasswordAuthenticationToken(id, email, Collections.singletonList(simpleGrantedAuthority)); // spring security 권한 객체
    }
}