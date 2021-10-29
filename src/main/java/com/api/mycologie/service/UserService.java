package com.api.mycologie.service;

import com.api.mycologie.entity.UserEntity;
import com.api.mycologie.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@RequiredArgsConstructor
@Service
public class UserService {
    final UserRepository userRepository;

    public Iterable<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }
}
