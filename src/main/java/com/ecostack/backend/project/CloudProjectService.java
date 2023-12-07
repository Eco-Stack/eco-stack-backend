package com.ecostack.backend.project;

import com.ecostack.backend.cloudinstance.CloudInstance;
import com.ecostack.backend.cloudinstance.CloudInstanceRepository;
import com.ecostack.backend.cloudinstance.CloudInstanceService;
import com.ecostack.backend.cloudinstance.dto.CloudInstanceDto;
import com.ecostack.backend.mapper.ProjectMapper;
import com.ecostack.backend.project.dto.*;
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
    private final int MAX_PROJECTS_TO_SHOW = 10;

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

    public CloudProjectOutlineDto getProjectOutline(String projectId) {
        List<CloudProject> cloudProjects = cloudProjectRepository.findAll();
        List<CloudProjectMetricDto> mostInstanceCntProject = calMostInstanceCntProjects(cloudProjects);

        //TODO : 인스턴스 증가 부분 추가
        //TODO : 인스턴스 감소 부분 추가

        List<CloudProjectResourceAvgDto> mostResourceUsingProject = calMostResourceUsingProjects(cloudProjects);

        return CloudProjectOutlineDto.builder()
                .mostInstanceCntProject(mostInstanceCntProject)
                .mostResourceUsingProject(mostResourceUsingProject)
                .build();
    }

    public List<CloudProjectMetricDto> calMostInstanceCntProjects(List<CloudProject> cloudProjects) {
        List<CloudProjectMetricDto> cloudProjectMetricDtos = new ArrayList<>();

        for(CloudProject cloudProject : cloudProjects) {
            CloudProjectMetricDto cloudProjectMetricDto = CloudProjectMetricDto.builder()
                    .name(cloudProject.getName())
                    .value(cloudProject.getCloudInstanceIds().size())
                    .build();

            cloudProjectMetricDtos.add(cloudProjectMetricDto);
        }

        cloudProjectMetricDtos.sort(Comparator.comparing(CloudProjectMetricDto::getValue).reversed());

        return cloudProjectMetricDtos.subList(0,  Math.min(cloudProjectMetricDtos.size(), MAX_PROJECTS_TO_SHOW));
    }

    public List<CloudProjectResourceAvgDto> calMostResourceUsingProjects(List<CloudProject> cloudProjects) {
        List<CloudProjectResourceAvgDto> mostResourceUsingProjects = new ArrayList<>();

        for(CloudProject cloudProject : cloudProjects) {
            double cpuUtilizationAvg = 0;
            double memoryUtilizationAvg = 0;
//            double diskUtilizationAvg = 0;

            for(String cloudInstanceId : cloudProject.getCloudInstanceIds()) {
                CloudInstance cloudInstance = cloudInstanceRepository.findById(cloudInstanceId).orElseThrow();

                double cloudInstanceCpuUtilizationAvg = cloudInstanceService.calCloudInstanceMetricsAverage
                        (cloudInstance.getCpuUtilizationMetricIds());
                double cloudInstanceMemoryUtilizationAvg = cloudInstanceService.calCloudInstanceMetricsAverage
                        (cloudInstance.getMemoryUtilizationMetricIds());
//               double cloudInstanceDiskUtilizationAvg = cloudInstanceService.calCloudInstanceMetricsAverage(cloudInstance.getDiskUtilizationMetricIds());

                cpuUtilizationAvg += cloudInstanceCpuUtilizationAvg;
                memoryUtilizationAvg += cloudInstanceMemoryUtilizationAvg;
//                diskUtilizationAvg += cloudInstanceDiskUtilizationAvg;
            }
            int cloudInstanceCnt = cloudProject.getCloudInstanceIds().size();
            cpuUtilizationAvg /= cloudInstanceCnt;
            memoryUtilizationAvg /= cloudInstanceCnt;

            CloudProjectResourceAvgDto cloudProjectResourceAvgDto = CloudProjectResourceAvgDto.builder()
                    .cpuUtilization(cpuUtilizationAvg)
                    .memoryUtilization(memoryUtilizationAvg)
                    .cloudInstanceCnt(cloudInstanceCnt)
                    .build();

            mostResourceUsingProjects.add(cloudProjectResourceAvgDto);
        }
        mostResourceUsingProjects.sort(Comparator.comparing(CloudProjectResourceAvgDto::getCpuUtilization).reversed());

        return mostResourceUsingProjects.subList(0, Math.min(mostResourceUsingProjects.size(), MAX_PROJECTS_TO_SHOW));
    }

    public List<CloudInstanceDto> calResourceIntensiveCloudInstances(Set<String> instanceIdList) {
        List<CloudInstanceDto> cloudInstanceDtos = new ArrayList<>();

         for(String instanceId : instanceIdList) {

             CloudInstance cloudInstance = cloudInstanceRepository.findById(instanceId).orElseThrow();
             double cpuUsage = cloudInstanceService.calCloudInstanceMetricsAverage(cloudInstance.getCpuUtilizationMetricIds());
//             double diskUsage = cloudInstanceService.calMetricsAverage(cloudInstance.getDiskUtilizationMetricIds());
             double memoryUsageInBytes = cloudInstanceService.calCloudInstanceMetricsAverage(cloudInstance.getMemoryUtilizationMetricIds());
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
