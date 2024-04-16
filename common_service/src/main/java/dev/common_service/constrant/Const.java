package dev.common_service.constrant;

public class Const {
    public static final class TOKEN{
        public static final String JWT_NAME = "jwt";
        public static final int JWT_MAX_AGE = 3600;
        public static final String JWT_PATH = "/";
        public static final String DOMAIN = "localhost";
        public static final String FULL_NAME_CLAIM = "fullName";
        public static final String PROVIDE_CLAIM = "provider";
        public static final String AUTH_HEADER = "Authorization";
        public static final String BEARER = "Bearer";
        public static final int INDEX_OF_JWT = 7;
        public static final String USER_IN_HEADER = "X-USER";
    }
}
