package com.ecostack.backend.cloudinstance;

import com.ecostack.backend.cloudinstance.dto.CloudInstanceMetric;
import com.ecostack.backend.metric.InstanceMetric.InstanceMetric;
import com.ecostack.backend.metric.InstanceMetric.InstanceMetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CloudInstanceService {

    private final InstanceMetricRepository instanceMetricRepository;

    public CloudInstanceMetric getMetricGraphFor(String cloudInstanceId, int days) {


        return null;
    }

    public double calMetricsAverage(Set<String> metricIds) {

        double metricsAverage = 0;

        for(String metricId : metricIds) {
            InstanceMetric instanceMetric = instanceMetricRepository.findById(metricId).orElseThrow();

            metricsAverage += instanceMetric.getMetricValues().getAverage();
        }

        return metricsAverage/metricIds.size();
    }
}
