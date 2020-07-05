package com.ms.wms.domain.member.application;

import com.ms.wms.domain.member.controller.dto.MemberDetailDto;
import com.ms.wms.domain.member.domain.Member;
import com.ms.wms.domain.member.domain.MemberRepository;
import com.ms.wms.global.config.exception.business.NoExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    public MemberDetailDto getMySession(Long memberId) {
        Member member =  memberRepository.findById(memberId)
                .orElseThrow(() -> new NoExistException("존재하지 않는 회원 입니다."));

        return MemberDetailDto.createMemberDetailDto(member.getEmail(), member.getName(), member.getRole());
    }

}
