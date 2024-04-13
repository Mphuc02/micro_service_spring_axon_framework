package dev.auth_service.handler;

import dev.auth_service.entity.User;
import dev.auth_service.model.OAuth2UserCustom;
import dev.auth_service.service.JwtService;
import dev.auth_service.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Inet4Address;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticateSuccessHandler implements AuthenticationSuccessHandler {
    private final Logger logger = LoggerFactory.getLogger(OAuth2AuthenticateSuccessHandler.class);
    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        logger.info("logged success by oauth2");
        OAuth2UserCustom loggedUser = (OAuth2UserCustom) authentication.getPrincipal();
        User user = User
                        .builder()
                        .fullName(loggedUser.getName())
                        .provider(loggedUser.getProvider())
                        .email(loggedUser.getEmail())
                        .build();
        //check if this is the first time user logged to system
        user.setId(this.userService.processOAuthAfterLogin(user));

//        String jwt = this.jwtService.generateToken(user);
//        Cookie jwtCookie = new Cookie(TOKEN.JWT_NAME, jwt);
//        jwtCookie.setDomain(TOKEN.DOMAIN);
//        jwtCookie.setPath(TOKEN.JWT_PATH);
//        jwtCookie.setMaxAge(TOKEN.JWT_MAX_AGE);
//        response.addCookie(jwtCookie);
//
//        Inet4Address address = (Inet4Address) Inet4Address.getLocalHost();
//        response.sendRedirect(String.format("http://%s:8888", address.getHostAddress()));
    }
}