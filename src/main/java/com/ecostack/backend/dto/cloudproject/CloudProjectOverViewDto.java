package com.ecostack.backend.dto.cloudproject;

import lombok.Builder;
import lombok.Getter;

import java.time.*;

@Getter
public class CloudProjectOverViewDto {
    private String id;
    private String name;
    private int instanceCnt;
    private LocalDate createdDate;
    private String owner;

    @Builder
    public CloudProjectOverViewDto(String id, String name, int instanceCnt, LocalDate createdDate, String owner) {
        this.id = id;
        this.name = name;
        this.instanceCnt = instanceCnt;
        this.createdDate = createdDate;
        this.owner = owner;
    }
}
