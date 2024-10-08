package dev.auth_service.query.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class GetAuthenticationQuery {
    private UUID id;
    private String jwtToken;
}