package dev.auth_service.common.util;

import dev.auth_service.common.constranst.Const.*;
import dev.common_service.exception.BadRequestException;
import dev.common_service.exception.ErrorMessages;
import jakarta.servlet.http.HttpServletRequest;

public class JwtUtils {
    public static String getJwtFromHeader(HttpServletRequest request){
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith(AUTH.BEARER)) {
            throw new BadRequestException(ErrorMessages.JWT_NOT_INCLUDE);
        }

        String jwt = authHeader.substring(AUTH.INDEX_OF_JWT);
        if(jwt.equals("null"))
            throw new BadRequestException(ErrorMessages.JWT_NOT_INCLUDE);
        return jwt;
    }
}