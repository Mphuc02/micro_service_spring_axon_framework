package dev.auth_service.command.event;

import dev.auth_service.common.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreatedEvent {
    private UserDto user;
}