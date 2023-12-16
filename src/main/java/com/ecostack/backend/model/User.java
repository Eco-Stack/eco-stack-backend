package com.ecostack.backend.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String loginId;
    String password;
    @Enumerated(EnumType.STRING)
    Role role;
}
