package com.example.security.service;

import com.example.security.dto.JoinDTO;
import com.example.security.entity.UserEntity;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class JoinService {

    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
    public void joinProcess(JoinDTO joinDTO){
        // 1. DB애 동일한 username을 가진 회원이 존재하는지 확인
        boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());
        if(isUser) {
            return;
        }

        // 2. 데이터를 회원 정보에 저장
        UserEntity data = new UserEntity();

        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);
    }
}
