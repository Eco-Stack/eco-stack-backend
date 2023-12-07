package com.ecostack.backend.project;

import com.ecostack.backend.cloudinstance.CloudInstance;
import com.ecostack.backend.cloudinstance.CloudInstanceRepository;
import com.ecostack.backend.cloudinstance.CloudInstanceService;
import com.ecostack.backend.cloudinstance.dto.CloudInstanceDto;
import com.ecostack.backend.mapper.ProjectMapper;
import com.ecostack.backend.project.dto.ProjectDashboardDto;
import com.ecostack.backend.project.dto.ProjectOverViewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        List<CloudInstanceDto> resourceIntensiveCloudInstances = calResourceIntensiveCloudInstances(cloudProject.getInstanceIdList());

        return ProjectDashboardDto.builder()
                .instanceCnt(cloudProject.getInstanceIdList().size())
                //TODO : instanceDiff 계산
                .resourceIntensiveCloudInstances(resourceIntensiveCloudInstances)
                .build();
    }

    public List<CloudInstanceDto> calResourceIntensiveCloudInstances(List<String> instanceIdList) {
        List<CloudInstanceDto> cloudInstanceDtos = new ArrayList<>();

         for(String instanceId : instanceIdList) {
             CloudInstance cloudInstance = cloudInstanceRepository.findById(instanceId).orElseThrow();

             double cpuUsage = cloudInstanceService.calMetricsAverage(cloudInstance.getCpuUtilizationMetricIds());
             double diskUsage = cloudInstanceService.calMetricsAverage(cloudInstance.getDiskUsageMetricIds());
             double memoryUsageInBytes = cloudInstanceService.calMetricsAverage(cloudInstance.getMemoryUtilizationMetricIds());

             CloudInstanceDto cloudInstanceDto = CloudInstanceDto.builder()
                     .id(cloudInstance.getId())
                     .cpuUsage(cpuUsage)
                     .diskUsage(diskUsage)
                     .memoryUsageInBytes(memoryUsageInBytes)
                     .build();

             cloudInstanceDtos.add(cloudInstanceDto);
         }

        //TODO : Top10 기준 생성 -> 그 기준으로 정렬
         return cloudInstanceDtos;
    }
}
