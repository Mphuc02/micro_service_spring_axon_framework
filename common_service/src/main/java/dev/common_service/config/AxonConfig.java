package dev.common_service.config;

import com.thoughtworks.xstream.XStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    @Bean
    public XStream xStream(){
        XStream xStream = new XStream();
        xStream.allowTypesByWildcard(new String[] {
                "dev.common_service.**",
                "dev.auth_service.**",//Tên package
                "dev.quiz_lab.**"
        });
        //Không có cấu hình này thì sẽ xảy ra lỗi Xstream.ForbiddenClass
        return xStream;
    }
}