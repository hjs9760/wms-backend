package com.ms.wms.security;

import com.ms.wms.security.oauth2.MyAuthenticationSuccessHandler;
import com.ms.wms.security.oauth2.custom.GoogleOAuth2User;
import com.ms.wms.security.oauth2.custom.KakaoOAuth2User;
import com.ms.wms.security.oauth2.custom.NaverOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by JaeeonJin on 2018-08-02.
 */
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 로그인 disable 처리
         */
        //security.httpBasic().disable();
        http.cors().and();
        http.csrf().disable();

        // authenticate
        http.authorizeRequests()
                .antMatchers("/my").permitAll()
                //.antMatchers("/").permitAll()
                .antMatchers("/oauth2/**").permitAll()
                .antMatchers("/login/**").permitAll()
                //.anyRequest().authenticated()
                .and()
                // oauth2 login 설정
                .oauth2Login()
                .successHandler(new MyAuthenticationSuccessHandler())
                // customUserType을 추가하면, 내부적으로 'CustomUserTypesOAuth2UserService' 클래스 사용
                    .userInfoEndpoint()
                        .customUserType(KakaoOAuth2User.class, "kakao")
                        .customUserType(NaverOAuth2User.class, "naver");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

}