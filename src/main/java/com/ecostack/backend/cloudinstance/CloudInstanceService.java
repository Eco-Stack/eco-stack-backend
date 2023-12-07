package com.ecostack.backend.cloudinstance;

import com.ecostack.backend.cloudinstance.dto.CloudInstanceMetricDto;
import com.ecostack.backend.metric.MetricValues;
import com.ecostack.backend.metric.cloudinstance.CloudInstanceMetric;
import com.ecostack.backend.metric.cloudinstance.CloudInstanceMetricRepository;
import com.ecostack.backend.metric.hypervisorInstanceMetric.HypervisorInstanceMetric;
import com.ecostack.backend.metric.hypervisorInstanceMetric.HypervisorInstanceMetricRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Slf4j
public class CloudInstanceService {

    private final CloudInstanceMetricRepository cloudInstanceMetricRepository;
    private final HypervisorInstanceMetricRepository hypervisorInstanceMetricRepository;
    private final CloudInstanceRepository cloudInstanceRepository;

    public CloudInstanceMetricDto getCloudInstanceMetricGraphFor(String cloudInstanceId, int days) {
        CloudInstance cloudInstance = cloudInstanceRepository.findById(cloudInstanceId).orElseThrow();
        List<MetricValues> cpuUtilizationMetrics = getCloudInstanceMetricFor(cloudInstance.getCpuUtilizationMetricIds(), days);
        List<MetricValues> memoryUtilizationMetrics = getCloudInstanceMetricFor(cloudInstance.getMemoryUtilizationMetricIds(), days);
//        List<MetricValues> diskUtilizationMetrics = getCloudInstanceMetricFor(cloudInstance.getDiskUtilizationMetricIds(), days);

        return CloudInstanceMetricDto.builder()
                .cpuUtilizationMetricValues(cpuUtilizationMetrics)
                .memoryUtilizationMetricValues(memoryUtilizationMetrics)
                .build();
    }

    public List<MetricValues> getCloudInstanceMetricFor(Set<String> metricIds, int days) {
        List<MetricValues> metricValuesForDays = new ArrayList<>();
        List<String> metricIdsList = new ArrayList<>(metricIds);
        int start = Math.max(0, metricIdsList.size() - days);

        for(int i=start; i<metricIdsList.size(); i++) {
            CloudInstanceMetric cloudInstanceMetric = cloudInstanceMetricRepository.findById(metricIdsList.get(i)).orElseThrow();
            metricValuesForDays.add(cloudInstanceMetric.getMetricValues());
        }

        return metricValuesForDays;
    }

    public double calCloudInstanceMetricsAverage(Set<String> metricIds) {

        double metricsAverage = 0;

        for(String metricId : metricIds) {
            CloudInstanceMetric cloudInstanceMetric = cloudInstanceMetricRepository.findById(metricId).orElseThrow();

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
