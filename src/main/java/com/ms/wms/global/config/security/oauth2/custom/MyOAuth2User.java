package com.ms.wms.global.config.security.oauth2.custom;

import org.springframework.security.oauth2.core.user.OAuth2User;

public abstract class MyOAuth2User implements OAuth2User {

   public Long dbPK;
}
