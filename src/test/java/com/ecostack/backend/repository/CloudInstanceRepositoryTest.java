package com.ecostack.backend.repository;

import com.ecostack.backend.cloudinstance.CloudInstance;
import com.ecostack.backend.cloudinstance.CloudInstanceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Set;

@DataMongoTest
public class CloudInstanceRepositoryTest {

    @Autowired
    CloudInstanceRepository cloudInstanceRepository;

    @Test
    void instance를_저장한다() {
        //given when
        CloudInstance savedInstanceDocument = cloudInstanceRepository.save(getInstance());

        //then
        Assertions.assertThat(savedInstanceDocument).isNotNull();
        Assertions.assertThat(savedInstanceDocument.getCpuUtilizationMetricIds().size()).isEqualTo(3);
    }

    public static CloudInstance getInstance() {

        return CloudInstance.builder()
                .cpuUtilizationMetricIds(Set.of("1L", "2L", "3L"))
                .memoryUtilizationMetricIds(Set.of("1L", "2L"))
                .build();
    }
}
