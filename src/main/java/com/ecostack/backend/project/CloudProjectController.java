package com.ecostack.backend.project;

import com.ecostack.backend.project.dto.ProjectDashboardDto;
import com.ecostack.backend.project.dto.ProjectOverViewDto;
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
    public ResponseEntity<ProjectOverViewDto> getOverview(@PathVariable String projectId) {
        return ResponseEntity.ok(cloudProjectService.getProjectOverview(projectId));
    }

    @GetMapping("v1/projects/{projectId}/dashboards")
    public ResponseEntity<ProjectDashboardDto> getDashboard (@PathVariable String projectId) {
        return ResponseEntity.ok(cloudProjectService.getProjectDashboard(projectId));
    }
}

