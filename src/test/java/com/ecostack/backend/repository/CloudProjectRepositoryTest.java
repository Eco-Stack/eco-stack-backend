package com.ecostack.backend.repository;

import com.ecostack.backend.project.CloudProject;
import com.ecostack.backend.project.CloudProjectRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

@DataMongoTest
public class CloudProjectRepositoryTest {

    @Autowired
    CloudProjectRepository cloudProjectRepository;

    @Test
    public void project를_저장한다() {
        //given when
        CloudProject savedCloudProject = cloudProjectRepository.save(getProject());

        //then
        Assertions.assertThat(savedCloudProject).isNotNull();
    }

    public static CloudProject getProject() {
        List<Long> instanceIds = List.of(1L, 2L, 3L);

        return CloudProject.builder()
                .name("project 1")
                .instanceIdList(instanceIds)
                .build();
    }

}
