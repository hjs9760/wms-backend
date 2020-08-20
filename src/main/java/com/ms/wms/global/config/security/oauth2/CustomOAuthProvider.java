package com.ms.wms.global.config.security.oauth2;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

/**
 * ClientRegistration.java : OAuth2 제공자에 등록된 Client(WMS App)의 정보를 나타내는 클래스
 *
 *
 */

public enum CustomOAuthProvider {

    KAKAO {
        @Override
        public ClientRegistration.Builder getBuilder() {
            return getBuilder("kakao", ClientAuthenticationMethod.POST)
                    .scope("profile", "account_email") // 요청할 권한
                    .authorizationUri("https://kauth.kakao.com/oauth/authorize") // authorization code 얻는 API
                    .tokenUri("https://kauth.kakao.com/oauth/token") // access Token 얻는 API
                    .userInfoUri("https://kapi.kakao.com/v2/user/me")  // 유저 정보 조회 API
                    .clientId("59994ae756226d295c3a55de8f952a9d")
                    .clientSecret("szqzCbgHa0iiYIErIqN2bfq5hwRWb1TS")
                    .userNameAttributeName("id")  // userInfo API Response에서 얻어올 ID 프로퍼티
                    .clientName("Kakao");  // spring 내에서 인식할 OAuth2 Provider Name
        }
    },

    NAVER {
        @Override
        public ClientRegistration.Builder getBuilder() {
            return getBuilder("naver", ClientAuthenticationMethod.POST)
                    .scope("profile", "email")
                    .authorizationUri("https://nid.naver.com/oauth2.0/authorize")
                    .tokenUri("https://nid.naver.com/oauth2.0/token")
                    .userInfoUri("https://openapi.naver.com/v1/nid/me")
                    .clientId("Pl9lL3j0uQ7Hwdc3oMl_")
                    .clientSecret("4Y8vZ5YNBl")
                    .userNameAttributeName("id")
                    .clientName("Naver");
        }
    };

    private static final String DEFAULT_LOGIN_REDIRECT_URL = "{baseUrl}/login/oauth2/code/{registrationId}";

    protected final ClientRegistration.Builder getBuilder(String registrationId,
                                                          ClientAuthenticationMethod method) {

        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
        builder.clientAuthenticationMethod(method);
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
        builder.redirectUriTemplate(CustomOAuthProvider.DEFAULT_LOGIN_REDIRECT_URL);

        return builder;
    }

    public abstract ClientRegistration.Builder getBuilder();
}
