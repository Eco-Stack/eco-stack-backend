package com.ecostack.backend.metric;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MetricService {

    private final MetricRepository metricRepository;


}
