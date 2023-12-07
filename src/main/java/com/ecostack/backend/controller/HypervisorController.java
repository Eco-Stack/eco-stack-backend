package com.ecostack.backend.controller;

import com.ecostack.backend.dto.cloudinstance.CloudInstanceMetricDto;
import com.ecostack.backend.dto.hypervisor.HypervisorGraphMetricDto;
import com.ecostack.backend.service.HypervisorService;
import com.ecostack.backend.dto.hypervisor.HypervisorOverviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class HypervisorController {

    private final HypervisorService hypervisorService;

    @GetMapping("/v1/hypervisors/outlines")
    public ResponseEntity<HypervisorOverviewDto> getOutline() {

        return ResponseEntity.ok(hypervisorService.getOutline());
    }

    @GetMapping("/v1/hypervisors/{hypervisorId}")
    public ResponseEntity<HypervisorGraphMetricDto> getMetricGraph(@PathVariable String hypervisorId, @RequestParam int days) {
        return ResponseEntity.ok(hypervisorService.getMetricGraphFor(hypervisorId, days));
    }
}
