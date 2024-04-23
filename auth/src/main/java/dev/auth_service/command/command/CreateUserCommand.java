package dev.auth_service.command.command;

import dev.auth_service.common.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateUserCommand {
    @TargetAggregateIdentifier
    private UserDto user;
}