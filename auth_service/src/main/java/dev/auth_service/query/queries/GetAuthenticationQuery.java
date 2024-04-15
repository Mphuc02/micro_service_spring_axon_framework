package dev.auth_service.query.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class GetAuthenticationQuery {
    @TargetAggregateIdentifier
    private UUID id;
    private String jwtToken;
}