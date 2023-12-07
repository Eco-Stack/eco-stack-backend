package com.ecostack.backend.service;

import com.ecostack.backend.repository.CloudInstanceRepository;
import com.ecostack.backend.model.Hypervisor;
import com.ecostack.backend.repository.HypervisorRepository;
import com.ecostack.backend.dto.hypervisor.HypervisorMetricDto;
import com.ecostack.backend.dto.hypervisor.HypervisorOverviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HypervisorService {

    private final HypervisorRepository hypervisorRepository;
    private final CloudInstanceRepository cloudInstanceRepository;
    private final CloudInstanceService cloudInstanceService;
    private final int MAX_HYPERVISORS_TO_SHOW = 10;

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
