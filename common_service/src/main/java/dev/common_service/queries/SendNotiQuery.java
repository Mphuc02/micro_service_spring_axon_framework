package dev.common_service.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@AllArgsConstructor
@Getter
public class SendNotiQuery {
    private List<String> emails;
}