package com.ecostack.backend.controller;

import com.ecostack.backend.dto.hypervisor.HypervisorGraphMetricDto;
import com.ecostack.backend.service.HypervisorService;
import com.ecostack.backend.dto.hypervisor.HypervisorOverviewDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "HypervisorController", description = "하이퍼바이저 관련 컨트롤러")
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class HypervisorController {

    private final HypervisorService hypervisorService;

    @Operation(description = "하이퍼바이저 개요를 조회합니다")
    @GetMapping("/v1/hypervisors/outlines")
    public ResponseEntity<HypervisorOverviewDto> getOutline() {

        return ResponseEntity.ok(hypervisorService.getOutline());
    }

    @Operation(description = "하이퍼바이저 metric 에 대한 그래프 정보를 얻습니다")
    @GetMapping("/v1/hypervisors/{hypervisorId}")
    public ResponseEntity<HypervisorGraphMetricDto> getMetricGraph(@PathVariable String hypervisorId, @RequestParam int days) {
        return ResponseEntity.ok(hypervisorService.getMetricGraphFor(hypervisorId, days));
    }
}
