package com.ecostack.backend.project;

import com.ecostack.backend.cloudinstance.CloudInstance;
import com.ecostack.backend.cloudinstance.CloudInstanceRepository;
import com.ecostack.backend.cloudinstance.CloudInstanceService;
import com.ecostack.backend.cloudinstance.dto.CloudInstanceDto;
import com.ecostack.backend.mapper.ProjectMapper;
import com.ecostack.backend.project.dto.ProjectDashboardDto;
import com.ecostack.backend.project.dto.ProjectOverViewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class CloudProjectService {

    private final CloudInstanceService cloudInstanceService;
    private final CloudProjectRepository cloudProjectRepository;
    private final CloudInstanceRepository cloudInstanceRepository;

    public ProjectOverViewDto getProjectOverview(String projectId) {
        return ProjectMapper.INSTANCE.toProjectOverviewDto(cloudProjectRepository.findById(projectId).orElseThrow());
    }

    public ProjectDashboardDto getProjectDashboard(String projectId) {
        CloudProject cloudProject = cloudProjectRepository.findById(projectId).orElseThrow();
        List<CloudInstanceDto> resourceIntensiveCloudInstances = calResourceIntensiveCloudInstances(cloudProject.getCloudInstanceIds());

        return ProjectDashboardDto.builder()
                .instanceCnt(cloudProject.getCloudInstanceIds().size())
                //TODO : instanceDiff 계산
                .resourceIntensiveCloudInstances(resourceIntensiveCloudInstances)
                .build();
    }

    public List<CloudInstanceDto> calResourceIntensiveCloudInstances(Set<String> instanceIdList) {
        List<CloudInstanceDto> cloudInstanceDtos = new ArrayList<>();

         for(String instanceId : instanceIdList) {

             CloudInstance cloudInstance = cloudInstanceRepository.findById(instanceId).orElseThrow();
             double cpuUsage = cloudInstanceService.calMetricsAverage(cloudInstance.getCpuUtilizationMetricIds());
//             double diskUsage = cloudInstanceService.calMetricsAverage(cloudInstance.getDiskUtilizationMetricIds());
             double memoryUsageInBytes = cloudInstanceService.calMetricsAverage(cloudInstance.getMemoryUtilizationMetricIds());
//
             CloudInstanceDto cloudInstanceDto = CloudInstanceDto.builder()
                     .id(cloudInstance.getId())
                     .cpuUsage(cpuUsage)
//                     .diskUsage(diskUsage)
                     .memoryUsageInBytes(memoryUsageInBytes)
                     .build();

             cloudInstanceDtos.add(cloudInstanceDto);
         }

         //TODO : CPU 기준 내림차순
         cloudInstanceDtos.sort(Comparator.comparing(CloudInstanceDto::getCpuUsage).reversed());
         return cloudInstanceDtos;
    }
}
