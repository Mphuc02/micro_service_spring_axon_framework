package dev.auth_service.common.service;

import dev.auth_service.common.model.OAuth2UserCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        //This function is executed before the filter and stored this OAuth2 to Authentication
        OAuth2User user = super.loadUser(userRequest);
        String registrationID =  userRequest.getClientRegistration().getRegistrationId();
        log.info("User logged with oauth2:");
        return new OAuth2UserCustom(user, registrationID);
    }
}