package com.ecostack.backend.project.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class ProjectOverViewDto {
    private String id;
    private String name;
    private int instanceCnt;
    private LocalDate createdDate;
    private String owner;
}
