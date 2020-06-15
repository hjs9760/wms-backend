package com.ms.wms.global.config.security.oauth2;

import com.ms.wms.domain.member.application.MemberJoinedEvent;
import com.ms.wms.domain.member.domain.Member;
import com.ms.wms.domain.member.domain.MemberRepository;
import com.ms.wms.domain.member.domain.Role;
import com.ms.wms.global.config.security.oauth2.custom.MyOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;


@RequiredArgsConstructor
public class MyOAuth2AuthorizedClientService implements OAuth2AuthorizedClientService {

    private final MemberRepository memberRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient oAuth2AuthorizedClient, Authentication authentication) {

        String providerType = oAuth2AuthorizedClient.getClientRegistration().getRegistrationId();
        OAuth2AccessToken accessToken = oAuth2AuthorizedClient.getAccessToken();
        OAuth2RefreshToken refreshToken = oAuth2AuthorizedClient.getRefreshToken();

        MyOAuth2User oauth2User = (MyOAuth2User) authentication.getPrincipal();
        String id = oauth2User.getName();
        String name = oauth2User.getAttribute("name");
        String email = oauth2User.getAttribute("email");

        Member member = new Member(email, id, name, providerType, accessToken.getTokenValue(), refreshToken.getTokenValue(),
                                    email.equals("jungsun9760@naver.com") ? Role.ROLE_ADMIN : Role.ROLE_USER);

        memberRepository.save(member);
        oauth2User.dbPK  = member.getId();
        oauth2User.email  = member.getEmail();

        this.eventPublisher.publishEvent(new MemberJoinedEvent(member));
    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {
        throw new UnsupportedOperationException(); // 미구현
    }

    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String s, String s1) {
        throw new UnsupportedOperationException(); // 미구현
    }
}