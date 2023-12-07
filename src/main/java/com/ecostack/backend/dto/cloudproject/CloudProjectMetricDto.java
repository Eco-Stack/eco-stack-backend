package com.ecostack.backend.dto.cloudproject;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CloudProjectMetricDto {

    private String id;
    private String name;
    private int value;
}
