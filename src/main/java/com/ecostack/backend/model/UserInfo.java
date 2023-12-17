package com.ecostack.backend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Builder
@Getter
@Document(collection = "UserInfo")
public class UserInfo {

    @Id
    String id;
    String loginId;
    String password;
    String name;
    String role;
}
