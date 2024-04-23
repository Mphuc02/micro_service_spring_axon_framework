package dev.quiz_lab.common.config;

import dev.quiz_lab.common.handler.EventHandlerInterceptor;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Test {
    private final EventHandlerInterceptor interceptor;

//    @Bean
//    public ConfigurerModule processingGroupErrorHandlingConfigurerModule() {
//        return configurer -> configurer.eventProcessing(processingConfigurer -> processingConfigurer
//                                        .registerDefaultListenerInvocationErrorHandler(conf -> interceptor));
//    }

    // Default all processors to subscribing mode.
    @Autowired
    public void configure(EventProcessingConfigurer config) {
        config.usingSubscribingEventProcessors();
    }
}