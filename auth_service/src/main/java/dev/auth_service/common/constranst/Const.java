package dev.auth_service.common.constranst;

public class Const {
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
}
