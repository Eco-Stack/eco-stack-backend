package com.ecostack.backend.project.dto;

import com.ecostack.backend.cloudinstance.dto.CloudInstanceDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ProjectDashboardDto {
    int instanceCnt;
    @Builder.Default
    int instanceDiff = 0;
    List<CloudInstanceDto> resourceIntensiveCloudInstances;
}
