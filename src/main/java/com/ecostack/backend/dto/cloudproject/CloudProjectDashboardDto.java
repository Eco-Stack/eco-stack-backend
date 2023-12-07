package com.ecostack.backend.dto.cloudproject;

import com.ecostack.backend.dto.cloudinstance.CloudInstanceDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CloudProjectDashboardDto {
    int instanceCnt;
    @Builder.Default
    int instanceDiff = 0;
    List<CloudInstanceDto> resourceIntensiveCloudInstances;
}
