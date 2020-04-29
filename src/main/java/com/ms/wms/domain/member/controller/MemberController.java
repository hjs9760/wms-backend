package com.ms.wms.domain.member.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my")
public class MemberController {

    // == Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    public void getMySession(@AuthenticationPrincipal OAuth2User oAuth2User) {
        System.out.println("oauth 유저 현재 상태 : " + oAuth2User);
    }
}
