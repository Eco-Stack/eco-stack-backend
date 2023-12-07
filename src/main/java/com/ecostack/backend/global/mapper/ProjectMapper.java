package com.ecostack.backend.global.mapper;

import com.ecostack.backend.model.CloudProject;
import com.ecostack.backend.dto.cloudproject.CloudProjectOverViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(expression = "java(cloudProject.getCloudInstanceIds().size())", target = "instanceCnt")
    CloudProjectOverViewDto toProjectOverviewDto(CloudProject cloudProject);
}
