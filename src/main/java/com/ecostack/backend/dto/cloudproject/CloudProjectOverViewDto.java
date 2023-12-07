package com.ecostack.backend.dto.cloudproject;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class CloudProjectOverViewDto {
    private String id;
    private String name;
    private int instanceCnt;
    private LocalDate createdDate;
    private String owner;
}
