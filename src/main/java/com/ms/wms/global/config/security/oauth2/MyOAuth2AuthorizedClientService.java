package com.ms.wms.global.config.security.oauth2;

import com.ms.wms.domain.member.domain.Member;
import com.ms.wms.domain.member.domain.MemberRepository;
import com.ms.wms.global.config.security.oauth2.custom.MyOAuth2User;
import com.ms.wms.global.config.slack.SlackChannel;
import com.ms.wms.global.config.slack.SlackMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;


/**
 * Created by momentjin@gmail.com on 2019-12-11
 * Github : http://github.com/momentjin
 */

public class MyOAuth2AuthorizedClientService implements OAuth2AuthorizedClientService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private SlackMessage slackMessage;

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient oAuth2AuthorizedClient, Authentication authentication) {
        String providerType = oAuth2AuthorizedClient.getClientRegistration().getRegistrationId();
        OAuth2AccessToken accessToken = oAuth2AuthorizedClient.getAccessToken();
        OAuth2RefreshToken refreshToken = oAuth2AuthorizedClient.getRefreshToken();

        MyOAuth2User oauth2User = (MyOAuth2User) authentication.getPrincipal();
        String id = oauth2User.getName();
        String name = oauth2User.getAttribute("name");

        Member member = new Member(id, name, providerType, accessToken.getTokenValue(), refreshToken.getTokenValue());
        memberRepository.save(member);

        slackMessage.sendSlackMessage(SlackChannel.CHANNEL_MEMBER,name+ " 님이 wms에 회원이 되었습니다.");

        oauth2User.dbPK  = member.getId();
    }

    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String s, String s1) {
        throw new UnsupportedOperationException(); // 아직 구현되지음 않음
    }

    @Override
    public void removeAuthorizedClient(String s, String s1) {
        throw new UnsupportedOperationException(); // 아직 구현되지 않음
    }

}