package com.ecostack.backend.repository;

import com.ecostack.backend.cloudinstance.CloudInstanceDocument;
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
        CloudInstanceDocument savedInstanceDocument = cloudInstanceRepository.save(getInstance());

        //then
        Assertions.assertThat(savedInstanceDocument).isNotNull();
        Assertions.assertThat(savedInstanceDocument.getCpuMetricIds().size()).isEqualTo(3);
    }

    public static CloudInstanceDocument getInstance() {

        return CloudInstanceDocument.builder()
                .cpuMetricIds(List.of(1L, 2L, 3L))
                .memoryMetricIds(List.of(1L, 2L))
                .networkMetricIds(List.of(1L, 2L, 3L, 4L))
                .build();
    }
}
