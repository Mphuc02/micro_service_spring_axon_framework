package dev.auth_service.query.rest;

import dev.auth_service.common.dto.UserDto;
import dev.auth_service.common.util.JwtUtils;
import dev.auth_service.query.queries.AuthenticateQuery;
import dev.auth_service.query.queries.GetAuthenticationQuery;
import dev.common_service.model.UserCommon;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthQueryRest {
    private final QueryGateway queryGateway;

    @PostMapping("/authentication")
    public ResponseEntity<Object> authenticate(@RequestBody UserDto authenticateUser){
        AuthenticateQuery query = new AuthenticateQuery(authenticateUser);
        String jwt = queryGateway.query(query, ResponseTypes.instanceOf(String.class))
                                .join();
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/authenticated")
    public ResponseEntity<Object> test(HttpServletRequest request){
        String token = JwtUtils.getJwtFromHeader(request);
        GetAuthenticationQuery query = new GetAuthenticationQuery(UUID.randomUUID(), token);
        UserCommon result = queryGateway.query(query, ResponseTypes.instanceOf(UserCommon.class))
                                        .join();
        return ResponseEntity.ok(result);
    }
}