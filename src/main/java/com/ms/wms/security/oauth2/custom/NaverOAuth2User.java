package com.ms.wms.security.oauth2.custom;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
public class NaverOAuth2User extends MyOAuth2User {

    private NaverProperties response;

    @Override
    public Map<String, Object> getAttributes() {
        Map<String, Object> attrs = new HashMap<>();
        attrs.put("id", this.response.getId());
        attrs.put("name", this.response.getName());

        return attrs;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new OAuth2UserAuthority(getAttributes()));
    }

    @Override
    public String getName() {
        return this.response.getId();
    }

    @Getter
    public static class NaverProperties {
        private String id;
        private String name;
    }
}
