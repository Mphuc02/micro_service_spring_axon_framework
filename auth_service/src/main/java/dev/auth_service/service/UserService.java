package dev.auth_service.service;

import dev.auth_service.dto.UserDto;
import dev.auth_service.entity.User;
import dev.auth_service.model.Provider;
import dev.auth_service.repository.UserRepository;
import dev.common_service.exception.BadRequestException;
import dev.common_service.exception.ErrorMessages;
import dev.common_service.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    @Lazy
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public UserDto findByUserName(String userName) {
        User user = this.findUserEntityByUserName(userName) ;
        return new UserDto(user);
    }

    public void registerUser(UserDto registerUser) {
        //Todo: Validate information of User
        User user = User.builder()
                            .id(UUID.randomUUID())
                            .userName(registerUser.getUserName())
                            .passWord(this.passwordEncoder.encode(registerUser.getPassWord()))
                            .email(registerUser.getEmail())
                            .fullName(registerUser.getFullName())
                            .gender(registerUser.getGender())
                            .provider(Provider.LOCAL)
                            .build();
        this.userRepository.save(user);
        logger.info(LOG_MESSAGE.REGISTER_USER(registerUser.getUserName()));
    }

    public UUID processOAuthAfterLogin(User loggedUser) {
        Optional<User> findByEmail = this.userRepository.findByEmail(loggedUser.getEmail());
        if(findByEmail.isPresent()){
            return findByEmail.get().getId();
        }

        loggedUser.setId(UUID.randomUUID());
        logger.info("new user sign up by oauth2 with email: " + loggedUser.getEmail());
        return this.userRepository.save(loggedUser).getId();
    }

    public String authenticateUser(UserDto authenticateUser) {
        if(ObjectUtils.isEmpty(authenticateUser.getUserName()) || ObjectUtils.isEmpty(authenticateUser.getPassWord()))
            throw new NotFoundException(dev.common_service.exception.ErrorMessages.AUTHENTICATE_FAIL);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateUser.getUserName(), authenticateUser.getPassWord()));
        }catch (AuthenticationException e){
            logger.error("Authenticate exception" ,e);
            throw new BadRequestException(ErrorMessages.AUTHENTICATE_FAIL);
        }
        return jwtService.generateToken(findUserEntityByUserName(authenticateUser.getUserName()));
        return null;
    }

    private User findUserEntityByUserName(String userName){
        return this.userRepository
                .findByUserName(userName)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.AUTHENTICATE_FAIL));
    }
}