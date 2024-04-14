package dev.auth_service.command.event;

import dev.auth_service.common.dto.UserDto;
import dev.auth_service.common.entity.User;
import dev.auth_service.common.model.Provider;
import dev.auth_service.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserEventHandler {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @EventHandler
    public void handler(UserCreatedEvent event){
        UserDto registerDto = event.getUser();
        User user = User.builder()
                        .id(UUID.randomUUID())
                        .userName(registerDto.getUserName())
                        .passWord(passwordEncoder.encode(registerDto.getPassWord()))
                        .email(registerDto.getEmail())
                        .fullName(registerDto.getFullName())
                        .gender(registerDto.getGender())
                        .provider(Provider.LOCAL)
                        .build();
        userRepository.save(user);
        log.info("Registed successfully user with username: " + user.getUsername());
    }
}