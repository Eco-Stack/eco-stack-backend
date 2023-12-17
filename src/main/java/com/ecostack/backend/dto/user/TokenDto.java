package com.ecostack.backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class TokenDto {
    private String token;
}
