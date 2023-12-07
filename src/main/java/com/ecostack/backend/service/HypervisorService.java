package com.ecostack.backend.service;

import com.ecostack.backend.dto.hypervisor.HypervisorGraphMetricDto;
import com.ecostack.backend.global.mapper.MetricValuesMapper;
import com.ecostack.backend.model.*;
import com.ecostack.backend.repository.CloudInstanceRepository;
import com.ecostack.backend.repository.HypervisorMetricRepository;
import com.ecostack.backend.repository.HypervisorRepository;
import com.ecostack.backend.dto.hypervisor.HypervisorMetricDto;
import com.ecostack.backend.dto.hypervisor.HypervisorOverviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class HypervisorService {

    private final HypervisorRepository hypervisorRepository;
    private final CloudInstanceRepository cloudInstanceRepository;
    private final HypervisorMetricRepository hypervisorMetricRepository;
    private final CloudInstanceService cloudInstanceService;
    private final int MAX_HYPERVISORS_TO_SHOW = 10;

    public HypervisorGraphMetricDto getMetricGraphFor(String hypervisorId, int days) {
        Hypervisor hypervisor = hypervisorRepository.findById(hypervisorId).orElseThrow();
        List<MetricValues> cpuUtilizationMetrics = getHypervisorMetricFor(hypervisor.getCpuUtilizationMetricIds(), days);
        List<MetricValues> memoryUtilizationMetrics = getHypervisorMetricFor(hypervisor.getMemoryUtilizationMetricIds(), days);

        return HypervisorGraphMetricDto.builder()
                .cpuUtilizationMetricValues(MetricValuesMapper.INSTANCE.toMetricValuesDto(cpuUtilizationMetrics))
                .memoryUtilizationMetricValues(MetricValuesMapper.INSTANCE.toMetricValuesDto(memoryUtilizationMetrics))
                .build();
    }

    public List<MetricValues> getHypervisorMetricFor(Set<String> metricIds, int days) {
        List<MetricValues> metricValuesForDays = new ArrayList<>();
        List<String> metricIdsList = new ArrayList<>(metricIds);
        int start = Math.max(0, metricIdsList.size() - days);

        for(int i=start; i<metricIdsList.size(); i++) {
            HypervisorMetric hypervisorMetric = hypervisorMetricRepository.findById(metricIdsList.get(i)).orElseThrow();
            metricValuesForDays.add(hypervisorMetric.getMetricValues());
        }

        return metricValuesForDays;
    }

    public HypervisorOverviewDto getOutline() {

        List<Hypervisor> hypervisors = hypervisorRepository.findAll();
        List<HypervisorMetricDto> cpuUsageAverageMetrics = new ArrayList<>();
        List<HypervisorMetricDto> memoryUsageAverageInBytesMetrics = new ArrayList<>();
        List<HypervisorMetricDto> diskUsageAverageMetrics = new ArrayList<>();

        for(Hypervisor hypervisor : hypervisors) {
            //Hypervisor 마다 Metric 값들
            cpuUsageAverageMetrics.add(HypervisorMetricDto.builder()
                    .hypervisorName(hypervisor.getName())
                    .metric(calCpuUsageAverage(hypervisor))
                    .build());

            memoryUsageAverageInBytesMetrics.add(HypervisorMetricDto.builder()
                    .hypervisorName(hypervisor.getName())
                    .metric(calMemoryUsageInBytesAverage(hypervisor))
                    .build());

            diskUsageAverageMetrics.add(HypervisorMetricDto.builder()
                    .hypervisorName(hypervisor.getName())
                    .metric(calDiskUsageAverage(hypervisor))
                    .build());
        }

        //TODO : sorting
        cpuUsageAverageMetrics.sort(Comparator.comparing(HypervisorMetricDto::getMetric).reversed());
        memoryUsageAverageInBytesMetrics.sort(Comparator.comparing(HypervisorMetricDto::getMetric).reversed());
        diskUsageAverageMetrics.sort(Comparator.comparing(HypervisorMetricDto::getMetric).reversed());

        return HypervisorOverviewDto.builder()
                .cpuUsageAverageMetrics(cpuUsageAverageMetrics.subList(0, Math.min(cpuUsageAverageMetrics.size(), MAX_HYPERVISORS_TO_SHOW)))
                .memoryUsageAverageMetrics(memoryUsageAverageInBytesMetrics.subList(0, Math.min(memoryUsageAverageInBytesMetrics.size(), MAX_HYPERVISORS_TO_SHOW)))
                .diskUsageAverageMetrics(diskUsageAverageMetrics.subList(0, Math.min(diskUsageAverageMetrics.size(), MAX_HYPERVISORS_TO_SHOW)))
                .build();
    }

    public double calCpuUsageAverage(Hypervisor hypervisor) {

        return  hypervisor.getCloudInstanceIds().stream()
                .map(cloudInstanceId -> cloudInstanceRepository.findById(cloudInstanceId).orElseThrow())
                .mapToDouble(cloudInstanceDocument -> cloudInstanceService.calCloudInstanceMetricsAverage(cloudInstanceDocument.getCpuUtilizationMetricIds()))
                .average()
                .orElse(0);
    }

    public double calMemoryUsageInBytesAverage(Hypervisor hypervisor) {

        return  hypervisor.getCloudInstanceIds().stream()
                .map(cloudInstanceId -> cloudInstanceRepository.findById(cloudInstanceId).orElseThrow())
                .mapToDouble(cloudInstanceDocument -> cloudInstanceService.calCloudInstanceMetricsAverage(cloudInstanceDocument.getMemoryUtilizationMetricIds()))
                .average()
                .orElse(0);
    }

    public double calDiskUsageAverage(Hypervisor hypervisor) {

        return  hypervisor.getCloudInstanceIds().stream()
                .map(cloudInstanceId -> cloudInstanceRepository.findById(cloudInstanceId).orElseThrow())
                .mapToDouble(cloudInstanceDocument -> cloudInstanceService.calCloudInstanceMetricsAverage(cloudInstanceDocument.getDiskUtilizationMetricIds()))
                .average()
                .orElse(0);
    }
}
