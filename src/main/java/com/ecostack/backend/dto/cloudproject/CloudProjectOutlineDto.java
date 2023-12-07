package com.ecostack.backend.dto.cloudproject;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CloudProjectOutlineDto {
    List<CloudProjectMetricDto> mostInstanceCntProject;
    List<CloudProjectMetricDto> mostInstanceIncreaseProject;
    List<CloudProjectMetricDto> mostInstanceDecreaseProject;
    List<CloudProjectResourceAvgDto> mostResourceUsingProject;
}
