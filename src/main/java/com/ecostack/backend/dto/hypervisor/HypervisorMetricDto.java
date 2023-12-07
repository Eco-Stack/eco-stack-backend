package com.ecostack.backend.dto.hypervisor;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HypervisorMetricDto {
    String hypervisorName;
    double metric;
}
