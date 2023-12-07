package com.ecostack.backend.global.mapper;

import com.ecostack.backend.dto.cloudproject.CloudProjectOverViewDto;
import com.ecostack.backend.model.CloudProject;
import com.ecostack.backend.model.MetricValues;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MetricValuesMapper {

    MetricValuesMapper INSTANCE = Mappers.getMapper(MetricValuesMapper.class);

    CloudProjectOverViewDto toProjectOverviewDto(MetricValues cloudProject);
}
