package com.ecostack.backend.repository;

import com.ecostack.backend.cloudinstance.CloudInstance;
import com.ecostack.backend.cloudinstance.CloudInstanceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

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
        Assertions.assertThat(savedInstanceDocument.getCpuMetricIds().size()).isEqualTo(3);
    }

    public static CloudInstance getInstance() {

        return CloudInstance.builder()
                .cpuMetricIds(List.of(1L, 2L, 3L))
                .memoryMetricIds(List.of(1L, 2L))
                .networkMetricIds(List.of(1L, 2L, 3L, 4L))
                .build();
    }
}
