package com.ecostack.backend.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {

    private String id;
    private String loginId;
    private String password;
    private String name;
    private String role;
}
