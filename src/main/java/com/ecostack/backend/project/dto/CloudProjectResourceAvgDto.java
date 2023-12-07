package com.ecostack.backend.project.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CloudProjectResourceAvgDto {

    private String id;
    private double cpuUtilization;
    private double memoryUtilization;
    private double cloudInstanceCnt;
}
