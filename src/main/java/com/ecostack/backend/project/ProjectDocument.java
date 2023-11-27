package com.ecostack.backend.project;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@ToString
@Document(collection = "project")
public class ProjectDocument {

    @Id
    private String id;
    private String name;
    private LocalDate createdDate;
    private String Owner;
    private List<String> instanceIdList;
}
