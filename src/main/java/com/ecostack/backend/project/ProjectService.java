package com.ecostack.backend.project;

import com.ecostack.backend.cloudinstance.CloudInstanceDocument;
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
public class ProjectService {

    private final CloudInstanceService cloudInstanceService;
    private final ProjectRepository projectRepository;
    private final CloudInstanceRepository cloudInstanceRepository;

    public ProjectOverViewDto getProjectOverview(String projectId) {
        return ProjectMapper.INSTANCE.toProjectOverviewDto(projectRepository.findById(projectId).orElseThrow());
    }

    public ProjectDashboardDto getProjectDashboard(String projectId) {
        ProjectDocument projectDocument = projectRepository.findById(projectId).orElseThrow();

        List<CloudInstanceDto> resourceIntensiveCloudInstances = calResourceIntensiveCloudInstances(projectDocument.getInstanceIdList());

        return ProjectDashboardDto.builder()
                .instanceCnt(projectDocument.getInstanceIdList().size())
                //TODO : instanceDiff 계산
                .resourceIntensiveCloudInstances(resourceIntensiveCloudInstances)
                .build();
    }

    public List<CloudInstanceDto> calResourceIntensiveCloudInstances(List<String> instanceIdList) {
        List<CloudInstanceDto> cloudInstanceDtos = new ArrayList<>();

         for(String instanceId : instanceIdList) {
             CloudInstanceDocument cloudInstanceDocument = cloudInstanceRepository.findById(instanceId).orElseThrow();

             double cpuCore = cloudInstanceService.calMetricsAverage(cloudInstanceDocument.getCpuCoreMetricIds());
             double cpuUsage = cloudInstanceService.calMetricsAverage(cloudInstanceDocument.getCpuUsageMetricIds());
             double diskUsage = cloudInstanceService.calMetricsAverage(cloudInstanceDocument.getDiskUsageMetricIds());
             double memoryUsageInBytes = cloudInstanceService.calMetricsAverage(cloudInstanceDocument.getMemoryUsageInBytesMetricIds());

             CloudInstanceDto cloudInstanceDto = CloudInstanceDto.builder()
                     .id(cloudInstanceDocument.getId())
                     .cpuCore(cpuCore)
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
