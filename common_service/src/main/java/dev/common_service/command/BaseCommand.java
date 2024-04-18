package dev.common_service.command;

import dev.common_service.model.UserCommon;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseCommand {
    private UserCommon owner;
}