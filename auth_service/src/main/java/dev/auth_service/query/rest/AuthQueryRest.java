package dev.auth_service.query.rest;

import dev.auth_service.common.dto.UserDto;
import dev.auth_service.query.queries.AuthenticateQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthQueryRest {
    private final QueryGateway queryGateway;

    @PostMapping("/authentication")
    public ResponseEntity<Object> authenticate(@RequestBody UserDto authenticateUser){
        AuthenticateQuery query = new AuthenticateQuery(authenticateUser);
        return ResponseEntity.ok(queryGateway.query(query, ResponseTypes.instanceOf(String.class))
                                            .join());
    }
}