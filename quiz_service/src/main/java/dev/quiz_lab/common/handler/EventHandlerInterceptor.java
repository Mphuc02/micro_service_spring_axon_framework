package dev.quiz_lab.common.handler;

import dev.common_service.exception.BaseException;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;
import org.springframework.stereotype.Component;
import javax.annotation.Nonnull;

@Component
public class EventHandlerInterceptor implements ListenerInvocationErrorHandler {
    @Override
    public void onError(@Nonnull Exception exception, @Nonnull EventMessage<?> event, @Nonnull EventMessageHandler eventHandler) throws Exception {
        System.out.println("Co exception");
        if(exception instanceof BaseException){
            throw new CommandExecutionException(exception.getClass().getName(), exception, ((BaseException) exception).getErrorMessage());
        }
        throw exception;
    }

}
