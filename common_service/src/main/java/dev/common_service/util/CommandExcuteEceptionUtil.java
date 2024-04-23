package dev.common_service.util;

import dev.common_service.exception.BaseException;
import org.axonframework.commandhandling.CommandExecutionException;

public class CommandExcuteEceptionUtil {
    public static void createException(BaseException ex){
        throw new CommandExecutionException("Exception when create quiz", ex ,ex.getErrorMessage());

    }
}