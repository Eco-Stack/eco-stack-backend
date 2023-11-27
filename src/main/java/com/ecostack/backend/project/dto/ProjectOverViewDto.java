package com.ecostack.backend.project.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class ProjectOverViewDto {
    String id;
    String name;
    int instanceCnt;
    LocalDate createdDate;
    String Owner;
}
