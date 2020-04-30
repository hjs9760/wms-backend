package com.ms.wms.security.oauth2.custom;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// todo : 구글 안쓰지 않나. 제거해도 될듯?
public class GoogleOAuth2User implements OAuth2User {
    private String id;
    private GoogleProperties properties;

    @Override
    public Map<String, Object> getAttributes() {
        Map<String, Object> attrs = new HashMap<>();
        attrs.put("id", this.id);
        attrs.put("name", this.properties.getNickname());

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
    private static class GoogleProperties {
        private String nickname;
    }
}
