package com.ecostack.backend.mapper;

import com.ecostack.backend.project.CloudProject;
import com.ecostack.backend.project.dto.ProjectOverViewDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-07T14:59:49+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectOverViewDto toProjectOverviewDto(CloudProject cloudProject) {
        if ( cloudProject == null ) {
            return null;
        }

        ProjectOverViewDto.ProjectOverViewDtoBuilder projectOverViewDto = ProjectOverViewDto.builder();

        projectOverViewDto.id( cloudProject.getId() );
        projectOverViewDto.name( cloudProject.getName() );
        projectOverViewDto.lastCloudInstanceCnt( cloudProject.getLastCloudInstanceCnt() );
        projectOverViewDto.createdDate( cloudProject.getCreatedDate() );
        projectOverViewDto.owner(cloudProject.getOwner());
        projectOverViewDto.instanceCnt( cloudProject.getCloudInstanceIds().size() );

        return projectOverViewDto.build();
    }
}
