package dev.common_service.filter;

import dev.common_service.constrant.Const.*;
import dev.common_service.model.UserCommon;
import dev.common_service.queries.AuthenticationCommonQuery;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final QueryGateway queryGateway;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String jwt = this.getJwtFromHeader(request);
        if(jwt != null){
            AuthenticationCommonQuery query = new AuthenticationCommonQuery(UUID.randomUUID(), jwt);
            UserCommon loggedUser = queryGateway.query(query, ResponseTypes.instanceOf(UserCommon.class))
                                        .join();

            if(loggedUser != null){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loggedUser,
                        null,
                        loggedUser.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromHeader(HttpServletRequest request){
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith(TOKEN.BEARER)) {
            return null;
        }

        String jwt = authHeader.substring(TOKEN.INDEX_OF_JWT);
        return jwt.equals("null") ? null : jwt;
    }
//
//    private String getJwtFromCookie(HttpServletRequest request){
//        Cookie[] cookiesOfRequest = request.getCookies();
//
//        if(!ObjectUtils.isEmpty(cookiesOfRequest)){
//            for(Cookie cookie: cookiesOfRequest){
//                if(cookie.getName().equals("jwt")){
//                    return cookie.getValue();
//                }
//            }
//        }
//
//        return null;
//    }
}