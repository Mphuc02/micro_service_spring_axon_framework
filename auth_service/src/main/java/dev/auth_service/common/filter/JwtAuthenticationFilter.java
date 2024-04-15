package dev.auth_service.common.filter;

import dev.auth_service.common.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    private final JwtService jwtService;
    private final UserRepository userRepository;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

//        String requestPath = request.getServletPath();
//        if(requestPath.startsWith(AUTH.URL)){
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        final String jwt;
//        final String id;
//
////        if(requestPath.startsWith(WEB_SOCKET.END_POINT))
////            jwt = this.getJwtFromCookie(request);
////        else
//            jwt = this.getJwtFromHeader(request);
//
//        if(ObjectUtils.isEmpty(jwt)){
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//
//        try {
//             id = this.jwtService.extractID(jwt);
//        }
//        catch (MalformedJwtException e){
//            logger.error("Jwt exception", e);
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//            return;
//        }
//
//        if(id != null){
//            User user = this.userRepository.findById(UUID.fromString(id)).orElseThrow(() -> new NotFoundException(ErrorMessages.AUTHENTICATE_FAIL));
//            if(this.jwtService.isTokenValid(jwt, user)){
//                //If the fields below have null values, it may cause an exception when hashing User
//                if(!user.getProvider().equals(Provider.LOCAL)){
//                    user.setUserName("");
//                    user.setGender(0);
//                    user.setPassWord("");
//                }
//
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user,
//                                                                              null,
//                                                                                        user.getAuthorities());
//
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//                filterChain.doFilter(request, response);
//                return;
//            }
//        }
//
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        filterChain.doFilter(request, response);
    }

//    private String getJwtFromHeader(HttpServletRequest request){
//        final String authHeader = request.getHeader("Authorization");
//
//        if (authHeader == null || !authHeader.startsWith(AUTH.BEARER)) {
//            return null;
//        }
//
//        String jwt = authHeader.substring(AUTH.INDEX_OF_JWT);
//        return jwt.equals("null") ? null : jwt;
//    }
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