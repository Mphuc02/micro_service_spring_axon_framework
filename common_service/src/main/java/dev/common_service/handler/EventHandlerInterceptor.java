package dev.common_service.handler;

import dev.common_service.exception.BaseException;
import dev.common_service.exception.ObjectPropertiesException;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;
import org.springframework.stereotype.Component;
import javax.annotation.Nonnull;

@Component
@Slf4j
public class EventHandlerInterceptor implements ListenerInvocationErrorHandler {
    @Override
    public void onError(@Nonnull Exception exception, @Nonnull EventMessage<?> event, @Nonnull EventMessageHandler eventHandler) throws Exception {
        log.error("Exception from EventHandler", exception);
        if(exception instanceof BaseException){
            throw new CommandExecutionException(exception.getClass().getName(), exception, ((BaseException) exception).getErrorMessage());
        }
        else if(exception instanceof ObjectPropertiesException){
            throw new CommandExecutionException(exception.getClass().getName(), exception, ((ObjectPropertiesException) exception).getErrors());
        }
        throw exception;
    }

}
