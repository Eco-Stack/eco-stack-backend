package com.ecostack.backend.controller;

import com.ecostack.backend.service.HypervisorService;
import com.ecostack.backend.dto.hypervisor.HypervisorOverviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class HypervisorController {

    private final HypervisorService hypervisorService;

    @GetMapping("/v1/hypervisors/outlines")
    public ResponseEntity<HypervisorOverviewDto> getOutline() {

        return ResponseEntity.ok(hypervisorService.getOutline());
    }
}
