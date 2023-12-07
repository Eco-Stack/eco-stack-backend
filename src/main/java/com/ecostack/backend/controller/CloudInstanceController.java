package com.ecostack.backend.controller;

import com.ecostack.backend.service.CloudInstanceService;
import com.ecostack.backend.dto.cloudinstance.CloudInstanceGraphMetricDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CloudInstanceController", description = "클라우드 환경에 있는 인스턴스 관련 컨트롤러")
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CloudInstanceController {

    private final CloudInstanceService cloudInstanceService;

    @Operation(description = "클라우드의 인스턴스 metric 에 대한 그래프 정보를 얻습니다")
    @GetMapping("/v1/cloud-instances/{cloudInstanceId}")
    public ResponseEntity<CloudInstanceGraphMetricDto> getMetricGraph(@PathVariable String cloudInstanceId, @RequestParam int days) {
        return ResponseEntity.ok(cloudInstanceService.getCloudInstanceMetricGraphFor(cloudInstanceId, days));
    }
}
