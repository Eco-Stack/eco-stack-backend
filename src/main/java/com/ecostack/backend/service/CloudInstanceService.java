package com.ecostack.backend.service;

import com.ecostack.backend.global.mapper.MetricValuesMapper;
import com.ecostack.backend.model.*;
import com.ecostack.backend.repository.CloudInstanceRepository;
import com.ecostack.backend.dto.cloudinstance.CloudInstanceGraphMetricDto;
import com.ecostack.backend.repository.CloudInstanceMetricRepository;
import com.ecostack.backend.repository.HypervisorInstanceMetricRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
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

    public CloudInstanceGraphMetricDto getCloudInstanceMetricGraphFor(String cloudInstanceId, int days) {
        CloudInstance cloudInstance = cloudInstanceRepository.findById(cloudInstanceId).orElseThrow();
        List<MetricValues> cpuUtilizationMetrics = getCloudInstanceMetricFor(cloudInstance.getCpuUtilizationMetricIds(), days);
        List<MetricValues> memoryUtilizationMetrics = getCloudInstanceMetricFor(cloudInstance.getMemoryUtilizationMetricIds(), days);
//        List<MetricValues> diskUtilizationMetrics = getCloudInstanceMetricFor(cloudInstance.getDiskUtilizationMetricIds(), days);

        return CloudInstanceGraphMetricDto.builder()
                .cpuUtilizationMetricValues(MetricValuesMapper.INSTANCE.toMetricValuesDto(cpuUtilizationMetrics))
                .memoryUtilizationMetricValues(MetricValuesMapper.INSTANCE.toMetricValuesDto(memoryUtilizationMetrics))
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
