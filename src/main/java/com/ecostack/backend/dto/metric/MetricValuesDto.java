package com.ecostack.backend.dto.metric;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@Getter
public class MetricValuesDto {

    @Builder.Default
    private List<MetricValueDto> metricValuesDto = new ArrayList<>();
}
