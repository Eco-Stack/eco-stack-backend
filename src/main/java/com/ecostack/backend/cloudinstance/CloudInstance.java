package com.ecostack.backend.cloudinstance;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Builder
@Getter
@Document(collection = "CloudInstance")
public class CloudInstance {

    @Id
    private String id;
    @Builder.Default
    private LocalDate createdDate = LocalDate.now(ZoneId.of("Asia/Seoul"));
    private List<String> cpuUtilizationMetricIds;
    private List<String> memoryUtilizationMetricIds;
    private List<String> diskUsageMetricIds;
}
