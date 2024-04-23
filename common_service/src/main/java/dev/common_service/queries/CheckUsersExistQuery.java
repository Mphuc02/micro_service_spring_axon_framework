package dev.common_service.queries;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CheckUsersExistQuery {
    private List<String> usernameList;
}