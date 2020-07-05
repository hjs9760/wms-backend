package com.ms.wms.domain.member.controller;

import com.ms.wms.domain.member.application.MemberService;
import com.ms.wms.domain.member.controller.dto.MemberDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/info")
    public MemberDetailDto getMySession(@AuthenticationPrincipal Long memberId) {
        return memberService.getMySession(memberId);
    }

}
