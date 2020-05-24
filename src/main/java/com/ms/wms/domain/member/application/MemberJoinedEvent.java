package com.ms.wms.domain.member.application;

import com.ms.wms.domain.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberJoinedEvent {

    private Member member;

    public MemberJoinedEvent(Member member) {
        this.member = member;
    }
}
