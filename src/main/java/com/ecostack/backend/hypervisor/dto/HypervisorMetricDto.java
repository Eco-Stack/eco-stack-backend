package com.ecostack.backend.hypervisor.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HypervisorMetricDto {
    String hypervisorName;
    double metric;
}
