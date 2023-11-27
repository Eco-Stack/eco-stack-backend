package com.ecostack.backend.metric;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Metric {

    private LocalDateTime dateTime;
    private Double value;
}
