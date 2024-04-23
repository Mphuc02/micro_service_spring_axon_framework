package dev.common_service.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsersExistResponse {
    private boolean isSuccess;
    private List<String> listError;

    public UsersExistResponse(){
        isSuccess = true;
        listError = new ArrayList<>();
    }
}