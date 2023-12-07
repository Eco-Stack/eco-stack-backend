package com.ecostack.backend.controller;

import com.ecostack.backend.service.CloudProjectService;
import com.ecostack.backend.dto.cloudproject.CloudProjectOutlineDto;
import com.ecostack.backend.dto.cloudproject.CloudProjectDashboardDto;
import com.ecostack.backend.dto.cloudproject.CloudProjectOverViewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CloudProjectController {

    private final CloudProjectService cloudProjectService;

    @GetMapping("/v1/projects/{projectId}/overviews")
    public ResponseEntity<CloudProjectOverViewDto> getOverview(@PathVariable String projectId) {
        return ResponseEntity.ok(cloudProjectService.getProjectOverview(projectId));
    }

    @GetMapping("/v1/projects/{projectId}/dashboards")
    public ResponseEntity<CloudProjectDashboardDto> getDashboard (@PathVariable String projectId) {
        return ResponseEntity.ok(cloudProjectService.getProjectDashboard(projectId));
    }

    @GetMapping("/v1/projects/outlines")
    public CloudProjectOutlineDto getOutline() {
        return cloudProjectService.getProjectOutline();
    }
}

