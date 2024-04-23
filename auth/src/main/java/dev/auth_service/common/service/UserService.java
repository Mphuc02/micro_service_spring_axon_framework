package dev.auth_service.common.service;

import org.springframework.stereotype.Service;

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