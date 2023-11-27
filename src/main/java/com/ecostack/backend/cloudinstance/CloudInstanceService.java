package com.ecostack.backend.cloudinstance;

import com.ecostack.backend.cloudinstance.dto.CloudInstanceMetric;
import com.ecostack.backend.metric.MetricDocument;
import com.ecostack.backend.metric.MetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CloudInstanceService {

    private final MetricRepository metricRepository;

    public CloudInstanceMetric getMetricGraphFor(String cloudInstanceId, int days) {


        return null;
    }

    public double calMetricsAverage(List<String> metricIds) {

        double metricsAverage = 0;

        for(String metricId : metricIds) {
            MetricDocument metricDocument = metricRepository.findById(metricId).orElseThrow();

            metricsAverage += metricDocument.getMetrics().getAverage();
        }

        return metricsAverage/metricIds.size();
    }
}
