package com.ms.wms.domain.member.application;

import com.ms.wms.domain.member.domain.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;


}
