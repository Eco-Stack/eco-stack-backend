package com.ecostack.backend.cloudinstance;

import com.ecostack.backend.metric.cloudinstance.CloudInstanceMetric;
import com.ecostack.backend.metric.cloudinstance.CloudInstanceMetricRepository;
import com.ecostack.backend.metric.hypervisorInstanceMetric.HypervisorInstanceMetric;
import com.ecostack.backend.metric.hypervisorInstanceMetric.HypervisorInstanceMetricRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
@Slf4j
public class CloudInstanceService {

    private final CloudInstanceMetricRepository cloudInstanceMetricRepository;
    private final HypervisorInstanceMetricRepository hypervisorInstanceMetricRepository;

    public com.ecostack.backend.cloudinstance.dto.CloudInstanceMetric getMetricGraphFor(String cloudInstanceId, int days) {


        return null;
    }

    public double calMetricsAverage(Set<String> metricIds) {

        double metricsAverage = 0;

        for(String metricId : metricIds) {
            CloudInstanceMetric cloudInstanceMetric = cloudInstanceMetricRepository.findById(metricId).orElseThrow();

            log.info(cloudInstanceMetric.toString());

            metricsAverage += cloudInstanceMetric.getMetricValues().getAverage();
        }

        return metricsAverage/metricIds.size();
    }

    public double calHypervisorMetricsAverage(Set<String> metricIds) {

        double metricsAverage = 0;

        for(String metricId : metricIds) {
            HypervisorInstanceMetric cloudInstanceMetric = hypervisorInstanceMetricRepository.findById(metricId).orElseThrow();

            metricsAverage += cloudInstanceMetric.getMetricValues().getAverage();
        }

        return metricsAverage/metricIds.size();
    }
}
