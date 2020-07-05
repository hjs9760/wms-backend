package com.ms.wms.domain.member.application;

import com.ms.wms.domain.member.domain.Member;
import com.ms.wms.domain.member.domain.MemberRepository;
import com.ms.wms.domain.member.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuth2MemberJoinService {

    private final MemberRepository memberRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Member join(String email, String oauth2Id, String name, String providerType, String accessToken, String refreshToken) {

        Optional<Member> optionalMember = memberRepository
                .findByOauthIdAndProviderName(oauth2Id, providerType);

        if (optionalMember.isPresent()) {
            optionalMember.get().updateOauth2Info(accessToken, refreshToken);
            return optionalMember.get();
        }

        // todo : 이메일에 따라 관리자 계정 가입 처리하는 부분 제거하기 (관리자용 회원가입 API를 만드는 게 더 낫다)
        Member newJoinedMeber = new Member(email, oauth2Id, name, providerType, accessToken, refreshToken,
                email.equals("jungsun9760@naver.com") ? Role.ROLE_ADMIN : Role.ROLE_USER);

        memberRepository.save(newJoinedMeber);
        this.eventPublisher.publishEvent(new MemberJoinedEvent(newJoinedMeber));

        return newJoinedMeber;
    }
}
