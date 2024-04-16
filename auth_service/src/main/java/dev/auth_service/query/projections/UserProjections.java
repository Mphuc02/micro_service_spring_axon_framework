package dev.auth_service.query.projections;

import dev.auth_service.common.dto.UserDto;
import dev.auth_service.common.entity.User;
import dev.auth_service.common.repository.UserRepository;
import dev.auth_service.common.service.JwtService;
import dev.auth_service.query.queries.AuthenticateQuery;
import dev.auth_service.query.queries.GetAuthenticationQuery;
import dev.common_service.exception.BadRequestException;
import dev.common_service.exception.ErrorMessages;
import dev.common_service.exception.NotFoundException;
import dev.common_service.model.UserCommon;
import dev.common_service.queries.AuthenticationCommonQuery;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserProjections {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @QueryHandler
    public String authenticateUser(AuthenticateQuery query) {
        UserDto authenticateUser = query.getAuthenticateUser();
        if(ObjectUtils.isEmpty(authenticateUser.getUserName()) || ObjectUtils.isEmpty(authenticateUser.getPassWord()))
            throw new NotFoundException(ErrorMessages.AUTHENTICATE_FAIL);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateUser.getUserName(), authenticateUser.getPassWord()));
        }catch (AuthenticationException e){
            log.error("Authenticate exception" ,e);
            throw new BadRequestException(ErrorMessages.AUTHENTICATE_FAIL);
        }
        return jwtService.generateToken(findUserByUserName(authenticateUser.getUserName()));
    }

    @QueryHandler
    public UserCommon query(GetAuthenticationQuery query){
        return getUserFromJwt(query.getJwtToken());
    }

    @QueryHandler
    public UserCommon query(AuthenticationCommonQuery query){
        return getUserFromJwt(query.getJwtToken());
    }

    private UserCommon getUserFromJwt(String token){
        User user = jwtService.isTokenValid(token);

        if(user == null)
            return null;

        return UserCommon.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .provider(user.getProvider())
                .build();
    }

    private User findUserByUserName(String userName){
        return userRepository
                .findByUserName(userName)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.AUTHENTICATE_FAIL));
    }

    private User findUserById(UUID id){
        return userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.AUTHENTICATE_FAIL));
    }
}