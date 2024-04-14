package dev.auth_service.command.rest;

import dev.auth_service.command.command.CreateUserCommand;
import dev.auth_service.common.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthCommandRest {
    private final CommandGateway commandGateway;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserDto registerUser){
        registerUser.setId(UUID.randomUUID());
        CreateUserCommand command = new CreateUserCommand(registerUser);
        commandGateway.sendAndWait(command);
        return ResponseEntity.ok("123");
    }
}