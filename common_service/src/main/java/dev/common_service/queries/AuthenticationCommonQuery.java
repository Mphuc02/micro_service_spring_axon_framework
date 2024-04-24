package dev.common_service.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class AuthenticationCommonQuery {
    private UUID id;
    private String jwtToken;
}