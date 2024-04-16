package dev.common_service.util;

import dev.common_service.constrant.Const.*;
import jakarta.servlet.http.HttpServletRequest;

public class JwtUtils {
    public static String getJwtFromHeader(HttpServletRequest request){
        final String authHeader = request.getHeader(TOKEN.AUTH_HEADER);
        if (authHeader == null || !authHeader.startsWith(TOKEN.BEARER)) {
            return null;
        }

        String jwt = authHeader.substring(TOKEN.INDEX_OF_JWT);
        if(jwt.equals("null"))
            return null;
        return jwt;
    }
}