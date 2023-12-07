package com.ecostack.backend.cloudinstance.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CloudInstanceDto {

    private String id;
    private double cpuUsage;
    private double memoryUsageInBytes;
    private double diskUsage;
}
