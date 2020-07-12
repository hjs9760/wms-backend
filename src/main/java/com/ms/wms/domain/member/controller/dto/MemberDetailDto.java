package com.ms.wms.domain.member.controller.dto;

import com.ms.wms.domain.member.domain.Role;
import lombok.Getter;

@Getter
public class MemberDetailDto {

    Long memberId;
    String email;
    String name;
    Role role;

    public static MemberDetailDto createMemberDetailDto(Long memberId, String email, String name, Role role) {
        MemberDetailDto dto = new MemberDetailDto();
        dto.memberId = memberId;
        dto.email = email;
        dto.name = name;
        dto.role = role;

        return dto;
    }
}
