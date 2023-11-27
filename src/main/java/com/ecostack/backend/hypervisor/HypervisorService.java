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

        List<HypervisorDocument> hypervisorDocuments = hypervisorRepository.findAll();
        List<HypervisorMetricDto> cpuUsageAverageMetrics = new ArrayList<>();
        List<HypervisorMetricDto> memoryUsageAverageInBytesMetrics = new ArrayList<>();
        List<HypervisorMetricDto> diskUsageAverageMetrics = new ArrayList<>();

        for(HypervisorDocument hypervisorDocument : hypervisorDocuments) {
            //Hypervisor 마다 Metric 값들
            cpuUsageAverageMetrics.add(HypervisorMetricDto.builder()
                    .hypervisorName(hypervisorDocument.getName())
                    .metric(calCpuUsageAverage(hypervisorDocument))
                    .build());

            memoryUsageAverageInBytesMetrics.add(HypervisorMetricDto.builder()
                    .hypervisorName(hypervisorDocument.getName())
                    .metric(calMemoryUsageInBytesAverage(hypervisorDocument))
                    .build());

            diskUsageAverageMetrics.add(HypervisorMetricDto.builder()
                    .hypervisorName(hypervisorDocument.getName())
                    .metric(calDiskUsageAverage(hypervisorDocument))
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

    public double calCpuUsageAverage(HypervisorDocument hypervisorDocument) {

        return  hypervisorDocument.getCloudInstanceIds().stream()
                .map(cloudInstanceId -> cloudInstanceRepository.findById(cloudInstanceId).orElseThrow())
                .mapToDouble(cloudInstanceDocument -> cloudInstanceService.calMetricsAverage(cloudInstanceDocument.getCpuUsageMetricIds()))
                .average()
                .orElse(0);
    }

    public double calMemoryUsageInBytesAverage(HypervisorDocument hypervisorDocument) {

        return  hypervisorDocument.getCloudInstanceIds().stream()
                .map(cloudInstanceId -> cloudInstanceRepository.findById(cloudInstanceId).orElseThrow())
                .mapToDouble(cloudInstanceDocument -> cloudInstanceService.calMetricsAverage(cloudInstanceDocument.getMemoryUsageInBytesMetricIds()))
                .average()
                .orElse(0);
    }

    public double calDiskUsageAverage(HypervisorDocument hypervisorDocument) {

        return  hypervisorDocument.getCloudInstanceIds().stream()
                .map(cloudInstanceId -> cloudInstanceRepository.findById(cloudInstanceId).orElseThrow())
                .mapToDouble(cloudInstanceDocument -> cloudInstanceService.calMetricsAverage(cloudInstanceDocument.getDiskUsageMetricIds()))
                .average()
                .orElse(0);
    }
}
