package dev.auth_service.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.auth_service.common.entity.User;
import dev.auth_service.common.model.Provider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private UUID id;
    private String userName;
    private String passWord;
    private String confirmPassWord;
    private String email;
    private String fullName;
    private Integer gender;
    private Provider provider;

    public UserDto(User entity){
        if(entity != null){
            this.id = entity.getId();
            this.userName = entity.getUsername();
            this.email = entity.getEmail();
            this.fullName = entity.getFullName();
            this.gender = entity.getGender();
        }
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", confirmPassWord='" + confirmPassWord + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", gender=" + gender +
                ", provider=" + provider +
                '}';
    }
}