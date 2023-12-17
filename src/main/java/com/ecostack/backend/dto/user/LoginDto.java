package com.ecostack.backend.dto.user;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private String loginId;
    private String password;
}
