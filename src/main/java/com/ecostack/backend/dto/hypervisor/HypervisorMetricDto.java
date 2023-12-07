package com.ecostack.backend.dto.hypervisor;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HypervisorMetricDto {
    String id;
    String hypervisorName;
    double metric;
}
