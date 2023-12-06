package com.ecostack.backend.metric;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@Getter
public class MetricValues {

    @Builder.Default
    private List<MetricValue> metricValueValues = new ArrayList<>();

    public double getSum() {
        return metricValueValues.stream().mapToDouble(MetricValue::getValue).sum();
    }

    public double getAverage() {
        return getSum()/ metricValueValues.size();
    }
}
