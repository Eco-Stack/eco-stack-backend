package com.ecostack.backend.dto.metric;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ToString
@Getter
public class MetricValueDto {

    private ZonedDateTime dateTime;
    private double value;

    @Builder
    public MetricValueDto(Instant dateTime, double value) {
        this.dateTime = dateTime.atZone(ZoneId.of("Asia/Seoul"));
        this.value = value;
    }
}
