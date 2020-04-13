package com.ms.wms.domain.Routine.controller;

import com.ms.wms.domain.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveRoutineDto {

    private Long id;
    private String name;
    private Member member;


}
