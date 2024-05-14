package dev.common_service.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedisProperties {
    private String host;
    private String port;

    public Integer getPort(){
        return Integer.parseInt(port);
    }
}