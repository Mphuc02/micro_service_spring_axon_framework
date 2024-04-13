package dev.auth_service.rest;

import dev.auth_service.dto.UserDto;
import dev.auth_service.entity.User;
import dev.auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping(AUTH.URL)
@RequestMapping("")
@RequiredArgsConstructor
public class AuthenticationRest {
    private final UserService userService;

//    @GetMapping(USER_API.AUTHENTICATED)
    @GetMapping()
    public ResponseEntity<Object> getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()){
            User authenticatedUser = (User) authentication.getPrincipal();
            return ResponseEntity.ok(UserDto.builder()
                    .id(authenticatedUser.getId())
                    .fullName(authenticatedUser.getFullName())
                    .build()
            );
        }

//        throw new BadRequestException(ErrorMessages.AUTHENTICATE_FAIL);
        return null;
    }

//    @PostMapping(AUTH.REGISTER_URL)
    @GetMapping()
    public ResponseEntity<Object> register(@RequestBody UserDto registerUser){
        this.userService.registerUser(registerUser);
        return ResponseEntity.ok("");
    }

//    @PostMapping(AUTH.AUTHENTICATE_URL)
    @GetMapping()
    public ResponseEntity<Object> authenticate(@RequestBody UserDto authenticateUser){
        return ResponseEntity.ok(userService.authenticateUser(authenticateUser));
    }
}