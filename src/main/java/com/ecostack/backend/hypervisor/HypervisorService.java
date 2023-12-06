package com.ecostack.backend.hypervisor;

import com.ecostack.backend.cloudinstance.CloudInstanceRepository;
import com.ecostack.backend.cloudinstance.CloudInstanceService;
import com.ecostack.backend.hypervisor.dto.HypervisorMetricDto;
import com.ecostack.backend.hypervisor.dto.HypervisorOverviewDto;
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

    public HypervisorOverviewDto getOverview() {

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
                .cpuUsageAverageMetrics(cpuUsageAverageMetrics)
                .memoryUsageAverageMetrics(memoryUsageAverageInBytesMetrics)
                .diskUsageAverageMetrics(diskUsageAverageMetrics)
                .build();
    }

    public double calCpuUsageAverage(Hypervisor hypervisor) {

        return  hypervisor.getCloudInstanceIds().stream()
                .map(cloudInstanceId -> cloudInstanceRepository.findById(cloudInstanceId).orElseThrow())
                .mapToDouble(cloudInstanceDocument -> cloudInstanceService.calMetricsAverage(cloudInstanceDocument.getCpuUtilizationMetricIds()))
                .average()
                .orElse(0);
    }

    public double calMemoryUsageInBytesAverage(Hypervisor hypervisor) {

        return  hypervisor.getCloudInstanceIds().stream()
                .map(cloudInstanceId -> cloudInstanceRepository.findById(cloudInstanceId).orElseThrow())
                .mapToDouble(cloudInstanceDocument -> cloudInstanceService.calMetricsAverage(cloudInstanceDocument.getMemoryUtilizationMetricIds()))
                .average()
                .orElse(0);
    }

    public double calDiskUsageAverage(Hypervisor hypervisor) {

        return  hypervisor.getCloudInstanceIds().stream()
                .map(cloudInstanceId -> cloudInstanceRepository.findById(cloudInstanceId).orElseThrow())
                .mapToDouble(cloudInstanceDocument -> cloudInstanceService.calMetricsAverage(cloudInstanceDocument.getDiskUsageMetricIds()))
                .average()
                .orElse(0);
    }
}
