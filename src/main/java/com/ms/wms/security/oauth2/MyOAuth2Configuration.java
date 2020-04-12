package com.ms.wms.security.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by momentjin@gmail.com on 2019-12-21
 * Github : http://github.com/momentjin
 */

@Configuration
public class MyOAuth2Configuration {

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        return new MyOAuth2AuthorizedClientService();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {

        CustomOAuthProvider[] customOAuthProviders = CustomOAuthProvider.values();

        List<ClientRegistration> registrations = new ArrayList<>();

        for (CustomOAuthProvider provider : customOAuthProviders) {
            ClientRegistration clientRegistration = provider
                    .getBuilder()
                    .build();

            registrations.add(clientRegistration);
        }

        return new InMemoryClientRegistrationRepository(registrations);
    }

}