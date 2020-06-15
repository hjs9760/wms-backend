package com.ms.wms.global.config.security.oauth2.custom;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
public class KakaoOAuth2User extends MyOAuth2User {

    private String id;
    private KakaoAccount kakao_account;

    @Override
    public Map<String, Object> getAttributes() {

        Map<String, Object> attrs = new HashMap<>();

        attrs.put("id", this.id);
        attrs.put("name", this.kakao_account.getProfile().getNickname());
        attrs.put("email", this.kakao_account.getEmail());

        return attrs;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new OAuth2UserAuthority(getAttributes()));
    }

    @Override
    public String getName() {
        return this.id;
    }

    @Getter
    private static class KakaoProperties {
        private String nickname;
    }

    @Getter
    private static class KakaoAccount {
        private String email;
        private KakaoProperties profile;
    }


}
