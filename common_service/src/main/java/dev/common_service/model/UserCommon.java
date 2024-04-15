package dev.common_service.model;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserCommon {
    private UUID id;
    private String fullName;
    private Provider provider;
}