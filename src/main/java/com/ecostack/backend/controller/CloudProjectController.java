package com.ecostack.backend.controller;

import com.ecostack.backend.service.CloudProjectService;
import com.ecostack.backend.dto.cloudproject.CloudProjectOutlineDto;
import com.ecostack.backend.dto.cloudproject.CloudProjectDashboardDto;
import com.ecostack.backend.dto.cloudproject.CloudProjectOverViewDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CloudProjectController", description = "클라우드가 속해있는 프로젝트 관련 컨트롤러")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CloudProjectController {

    private final CloudProjectService cloudProjectService;

    @Operation(description = "프로젝트 현황을 조회합니다")
    @GetMapping("/v1/projects/{projectId}/overviews")
    public ResponseEntity<CloudProjectOverViewDto> getOverview(@PathVariable String projectId) {
        return ResponseEntity.ok(cloudProjectService.getProjectOverview(projectId));
    }

    @Operation(description = "프로젝트 Dashboard를 조회합니다")
    @GetMapping("/v1/projects/{projectId}/dashboards")
    public ResponseEntity<CloudProjectDashboardDto> getDashboard (@PathVariable String projectId) {
        return ResponseEntity.ok(cloudProjectService.getProjectDashboard(projectId));
    }

    @Operation(description = "클라우드(프로젝트) 개요를 조회합니다")
    @GetMapping("/v1/projects/outlines")
    public CloudProjectOutlineDto getOutline() {
        return cloudProjectService.getProjectOutline();
    }
}

