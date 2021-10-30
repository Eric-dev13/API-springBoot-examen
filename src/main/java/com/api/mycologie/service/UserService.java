package com.api.mycologie.service;

import com.api.mycologie.entity.UserEntity;
import com.api.mycologie.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
public class UserService {
    final UserRepository userRepository;

    public Iterable<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(Long userId){
        return userRepository.findById(userId);
    }

    public UserEntity addUser(@RequestBody UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public void updateUser(final Long userId, UserEntity userEntity) {
        Optional<UserEntity> user = userRepository.findById(userId);
        // Mapping
        // user.name =  userEntity.name
        // ...
    }

    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }

}
