package dev.common_service.event;

import dev.common_service.model.UserCommon;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEvent {
    private long iat;
    private UserCommon owner;
}