package com.ecostack.backend.service;

import com.ecostack.backend.dto.user.UserDto;
import com.ecostack.backend.model.UserInfo;
import com.ecostack.backend.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserInfo signUp(UserDto userDto) {
        UserInfo userInfo = UserInfo.builder()
                .loginId(userDto.getLoginId())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .name(userDto.getName())
                .role(userDto.getRole())
                .build();

        return userInfoRepository.save(userInfo);
    }
}
