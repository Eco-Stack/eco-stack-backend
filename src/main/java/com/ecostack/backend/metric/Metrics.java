package com.ecostack.backend.metric;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Metrics {
    private List<Metric> metrics;

    public double getSum() {
        return metrics.stream().mapToDouble(Metric::getValue).sum();
    }

    public double getAverage() {
        return getSum()/metrics.size();
    }
}
