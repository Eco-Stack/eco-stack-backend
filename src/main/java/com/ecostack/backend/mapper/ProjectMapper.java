package com.ecostack.backend.mapper;

import com.ecostack.backend.project.CloudProject;
import com.ecostack.backend.project.dto.ProjectOverViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(expression = "java(projectDocument.getInstanceIdList().size())", target = "instanceCnt")
    ProjectOverViewDto toProjectOverviewDto(CloudProject cloudProject);
}
