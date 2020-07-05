package com.ms.wms.domain.member.controller.dto;

import com.ms.wms.domain.member.domain.Role;
import lombok.Getter;

@Getter
public class MemberDetailDto {

    String email;
    String name;
    Role role;

    public static MemberDetailDto createMemberDetailDto(String email, String name, Role role) {
        MemberDetailDto dto = new MemberDetailDto();
        dto.email = email;
        dto.name = name;
        dto.role = role;

        return dto;
    }
}
