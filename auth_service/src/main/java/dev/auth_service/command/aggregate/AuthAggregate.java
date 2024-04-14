package dev.auth_service.command.aggregate;

import dev.auth_service.command.event.UserCreatedEvent;
import dev.auth_service.command.command.CreateUserCommand;
import dev.auth_service.common.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@RequiredArgsConstructor
public class AuthAggregate {
    @AggregateIdentifier
    private UserDto user;

    @CommandHandler
    public AuthAggregate(CreateUserCommand command){
        UserCreatedEvent event = new UserCreatedEvent(command.getUser());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent event){
        this.user = event.getUser();
    }
}