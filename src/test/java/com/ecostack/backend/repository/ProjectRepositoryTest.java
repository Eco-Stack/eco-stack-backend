package com.ecostack.backend.repository;

import com.ecostack.backend.project.ProjectDocument;
import com.ecostack.backend.project.ProjectRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

@DataMongoTest
public class ProjectRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;

    @Test
    public void project를_저장한다() {
        //given when
        ProjectDocument savedProjectDocument = projectRepository.save(getProject());

        //then
        Assertions.assertThat(savedProjectDocument).isNotNull();
    }

    public static ProjectDocument getProject() {
        List<Long> instanceIds = List.of(1L, 2L, 3L);

        return ProjectDocument.builder()
                .name("project 1")
                .instanceIdList(instanceIds)
                .build();
    }

}
