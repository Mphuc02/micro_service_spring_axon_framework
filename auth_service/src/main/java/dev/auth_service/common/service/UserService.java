package dev.auth_service.common.service;

import dev.auth_service.common.dto.UserDto;
import dev.auth_service.common.entity.User;
import dev.auth_service.common.model.Provider;
import dev.auth_service.common.repository.UserRepository;
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
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//    private final Logger logger = LoggerFactory.getLogger(UserService.class);
//
//    public UserDto findByUserName(String userName) {
//        User user = this.findUserEntityByUserName(userName) ;
//        return new UserDto(user);
//    }
//
//    public void registerUser(UserDto registerUser) {
//        //Todo: Validate information of User
//        User user = User.builder()
//                            .id(UUID.randomUUID())
//                            .userName(registerUser.getUserName())
//                            .passWord(this.passwordEncoder.encode(registerUser.getPassWord()))
//                            .email(registerUser.getEmail())
//                            .fullName(registerUser.getFullName())
//                            .gender(registerUser.getGender())
//                            .provider(Provider.LOCAL)
//                            .build();
//        this.userRepository.save(user);
//        logger.info(LOG_MESSAGE.REGISTER_USER(registerUser.getUserName()));
//    }
//
//    public UUID processOAuthAfterLogin(User loggedUser) {
//        Optional<User> findByEmail = this.userRepository.findByEmail(loggedUser.getEmail());
//        if(findByEmail.isPresent()){
//            return findByEmail.get().getId();
//        }
//
//        loggedUser.setId(UUID.randomUUID());
//        logger.info("new user sign up by oauth2 with email: " + loggedUser.getEmail());
//        return this.userRepository.save(loggedUser).getId();
//    }
//

//
//
}