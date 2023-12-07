package com.ecostack.backend.project.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CloudProjectMetricDto {

    private String name;
    private int value;
}
