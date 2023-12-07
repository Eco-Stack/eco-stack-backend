package com.ecostack.backend.service;

import com.ecostack.backend.repository.CloudInstanceMetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InstanceMetricService {

    private final CloudInstanceMetricRepository cloudInstanceMetricRepository;


}
