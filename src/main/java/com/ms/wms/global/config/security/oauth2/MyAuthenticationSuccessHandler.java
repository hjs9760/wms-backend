package com.ms.wms.global.config.security.oauth2;

import com.ms.wms.global.config.security.jwt.WmsJWTGenerator;
import com.ms.wms.global.config.security.oauth2.custom.MyOAuth2User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        MyOAuth2User oAuth2User = (MyOAuth2User) authentication.getPrincipal();

        Long oauth2UserId = Long.parseLong(oAuth2User.getName());

        String jwt = WmsJWTGenerator.generate(oAuth2User.dbPK);

        response.setStatus(200);
        response.getWriter().write(jwt);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
