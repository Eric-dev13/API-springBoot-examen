package com.api.mycologie.service;

import com.api.mycologie.entity.UserEntity;
import com.api.mycologie.repository.UserRepository;
import com.api.mycologie.service.dto.UserDTO;
import com.api.mycologie.service.mapperdao.UserDAO;
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

    public UserDTO getUserById(Long userId){
        UserDAO userDAO = new UserDAO();
        Optional <UserEntity> optionalUserEntity = userRepository.findById(userId);
        return userDAO.entityMappingToDto(optionalUserEntity.get());
    }

    public UserEntity addUser(@RequestBody UserDTO userDTO) {
        UserDAO userDAO = new UserDAO();
        UserEntity userEntity = userDAO.dtoMappingToEntity(userDTO);
        return userRepository.save(userEntity);
    }

    public void updateUser(final Long userId, UserEntity userEntity) {
        // get userEntity  by userId
        Optional<UserEntity> optionalUser = userRepository.findById(userId);

        // Update methode
        userRepository.save(optionalUser.get());
    }

    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }

}
