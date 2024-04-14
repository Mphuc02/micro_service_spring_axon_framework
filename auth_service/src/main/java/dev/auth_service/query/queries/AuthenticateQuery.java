package dev.auth_service.query.queries;

import dev.auth_service.common.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthenticateQuery {
    private final UserDto authenticateUser;
}