package dev.common_service.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class AuthenticationCommonQuery {
    @TargetAggregateIdentifier
    private UUID id;
    private String jwtToken;
}