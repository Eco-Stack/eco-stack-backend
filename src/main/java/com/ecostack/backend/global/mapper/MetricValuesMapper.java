package com.ecostack.backend.global.mapper;

import com.ecostack.backend.dto.metric.MetricValueDto;
import com.ecostack.backend.dto.metric.MetricValuesDto;
import com.ecostack.backend.model.MetricValues;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MetricValuesMapper {

    MetricValuesMapper INSTANCE = Mappers.getMapper(MetricValuesMapper.class);

    @Mapping(source = "metricValues", target = "metricValuesDto")
    MetricValuesDto toMetricValuesDto(MetricValues metricValues);

    List<MetricValuesDto> toMetricValuesDto(List<MetricValues> metricValuesList);
}
