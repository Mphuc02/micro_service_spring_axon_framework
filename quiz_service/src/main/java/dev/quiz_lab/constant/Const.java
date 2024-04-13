package dev.quiz_lab.constant;

import lombok.extern.slf4j.Slf4j;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.UUID;

public class Const {
    @Slf4j
    public static final class QUIZ_API{
        public static final String URL = "/api/v1/quiz";
        public static String PLAY_URL(UUID id){
            try {
                return String.format(Inet4Address.getLocalHost().getHostAddress() + ":8080"  +  URL + "/play/%s", id.toString());
            } catch (UnknownHostException e) {
                log.error("UnknownHostException: ", e);
                throw new RuntimeException(e);
            }
        }
    }

    public static final class USER_API{
        public static final String URL = "/api/v1/user";
        public static final String AUTHENTICATED = "/authenticated";
    }

    public static final class EXCEL_INPUT{
        public static final int CONTENT = 0;
        public static final int NUMBER_OF_CHOICES = 1;
        public static final int INDEX_OF_ANSWER = 2;
        public static final int MAX_NUMBER_CHOICES = 7;
    }

    public static final class JWT{
        public static final String SECRET_KEY = "${application.security.jwt.secret-key}";
        public static final String EXPIRATION = "${application.security.jwt.expiration}";
        public static final String REFRESH_TOKEN_EXPIRATION = "${application.security.jwt.refresh-token.expiration}";
    }

    public static final class TOKEN{
        public static final String JWT_NAME = "jwt";
        public static final int JWT_MAX_AGE = 3600;
        public static final String JWT_PATH = "/";
        public static final String DOMAIN = "localhost";
        public static final String FULL_NAME_CLAIM = "fullName";
        public static final String PROVIDE_CLAIM = "provider";
    }


    public static final class OAUTH2_USER{
        public static final String NAME = "name";
        public static final String EMAIL = "email";
    }

    public static final class LOG_MESSAGE{
        public static String REGISTER_USER(String username){
            return String.format("Register success with username = %s", username);
        }
    }
}