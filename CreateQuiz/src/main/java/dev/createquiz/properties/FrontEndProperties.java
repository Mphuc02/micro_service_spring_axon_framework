package dev.createquiz.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "front-end.url")
public class FrontEndProperties {
    private String url;
    public String getFrontEndUrl(){
        return String.format("%s:8888", url);
    }
}
