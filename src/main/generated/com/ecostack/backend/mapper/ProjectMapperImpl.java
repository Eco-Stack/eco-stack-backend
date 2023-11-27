package com.ecostack.backend.mapper;

import com.ecostack.backend.project.ProjectDocument;
import com.ecostack.backend.project.dto.ProjectOverViewDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-11T10:31:22+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectOverViewDto toProjectOverviewDto(ProjectDocument projectDocument) {
        if ( projectDocument == null ) {
            return null;
        }

        ProjectOverViewDto.ProjectOverViewDtoBuilder projectOverViewDto = ProjectOverViewDto.builder();

        projectOverViewDto.id( projectDocument.getId() );
        projectOverViewDto.name( projectDocument.getName() );
        projectOverViewDto.createdDate( projectDocument.getCreatedDate() );

        projectOverViewDto.instanceCnt( projectDocument.getInstanceIdList().size() );

        return projectOverViewDto.build();
    }
}
