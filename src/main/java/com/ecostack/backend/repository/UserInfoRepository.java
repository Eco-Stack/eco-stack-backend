package com.ecostack.backend.repository;

import com.ecostack.backend.model.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserInfoRepository extends MongoRepository<UserInfo, String> {
    UserInfo findByName(String name);
    UserInfo findByLoginId(String loginId);
}
