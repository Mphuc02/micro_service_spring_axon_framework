package dev.auth_service.command.rest;

import dev.auth_service.command.command.CreateUserCommand;
import dev.auth_service.common.dto.UserDto;
import dev.auth_service.query.queries.AuthenticateQuery;
import dev.auth_service.query.queries.GetAuthenticationQuery;
import dev.common_service.exception.BadRequestException;
import dev.common_service.exception.ErrorMessages;
import dev.common_service.model.UserCommon;
import dev.common_service.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthCommandRest {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserDto registerUser){
        registerUser.setId(UUID.randomUUID());
        CreateUserCommand command = new CreateUserCommand(registerUser);
        commandGateway.sendAndWait(command);
        return ResponseEntity.ok("");
    }

    @PostMapping("/authentication")
    public ResponseEntity<Object> authenticate(@RequestBody UserDto authenticateUser){
        AuthenticateQuery query = new AuthenticateQuery(authenticateUser);
        String jwt = queryGateway.query(query, ResponseTypes.instanceOf(String.class))
                .join();
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/authenticated")
    public ResponseEntity<Object> getAuthenticatedInfo(HttpServletRequest request){
        String token = JwtUtils.getJwtFromHeader(request);
        if(ObjectUtils.isEmpty(token))
            throw new BadRequestException(ErrorMessages.JWT_NOT_INCLUDE);

        GetAuthenticationQuery query = new GetAuthenticationQuery(UUID.randomUUID(), token);
        UserCommon result = queryGateway.query(query, ResponseTypes.instanceOf(UserCommon.class))
                .join();
        return ResponseEntity.ok(result);
    }
}