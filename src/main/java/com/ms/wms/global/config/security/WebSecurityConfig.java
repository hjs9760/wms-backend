package com.ms.wms.global.config.security;

import com.ms.wms.global.config.security.jwt.JWTAuthorizationFilter;
import com.ms.wms.global.config.security.oauth2.MyAuthenticationSuccessHandler;
import com.ms.wms.global.config.security.oauth2.custom.KakaoOAuth2User;
import com.ms.wms.global.config.security.oauth2.custom.NaverOAuth2User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;
import java.util.List;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and();
        http.csrf().disable();

        // 요청에 대한 권한을 지정
        http.authorizeRequests()
                .antMatchers("/oauth2/**").permitAll()
                // 인증이 되어야 한다
                .anyRequest().authenticated()
                .accessDecisionManager(accessDecisionManager())  // 인증이 완료된 사용자가 리소스에 접근하려고 할때 해당 요청을 허용할것인지 판단하는 클래스(==인가)
                .and()
                /**
                 * UsernamePasswordAuthenticationFilter.class
                 *
                 * username, password를 쓰는 form기반 인증을 처리하는 필터
                 * AuthenticationManager를 통한 인증 실행
                 * 성공하면, Authentication 객체를 SecurityContext에 저장 후 AuthenticationSuccessHandler 실행
                 * 실패하면 AuthenticationFailureHandler 실행
                 *
                 */
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                // oauth2 login 에 대한 설정
                .oauth2Login()
                .successHandler(new MyAuthenticationSuccessHandler())
                    // OAuth2 로그인 성공 후 사용자 정보를 가져올 때의 설정
                    .userInfoEndpoint()
                        .customUserType(KakaoOAuth2User.class, "kakao")
                        .customUserType(NaverOAuth2User.class, "naver");
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_MANAGER > ROLE_USER");

        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy);

        WebExpressionVoter webExpressionVoter =  new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(handler);

        List<AccessDecisionVoter<? extends Object>> voterList = Collections.singletonList(webExpressionVoter);
        return new MyAffirmativeBased(voterList);

    }


}