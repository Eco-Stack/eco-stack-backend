package com.ecostack.backend.controller;

import com.ecostack.backend.service.CloudInstanceService;
import com.ecostack.backend.dto.cloudinstance.CloudInstanceMetricDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CloudInstanceController {

    private final CloudInstanceService cloudInstanceService;

    @GetMapping("/v1/cloud-instances/{cloudInstanceId}")
    public ResponseEntity<CloudInstanceMetricDto> getMetricGraph(@PathVariable String cloudInstanceId, @RequestParam int days) {
        return ResponseEntity.ok(cloudInstanceService.getCloudInstanceMetricGraphFor(cloudInstanceId, days));
    }
}
