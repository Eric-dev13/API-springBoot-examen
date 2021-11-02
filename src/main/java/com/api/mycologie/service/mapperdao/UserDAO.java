package com.api.mycologie.service.mapperdao;

import com.api.mycologie.entity.UserEntity;
import com.api.mycologie.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class UserDAO {
    //private final UserDTO userDTO;

    // Le pattern DAO (Data Access Object) permet de faire le lien entre la couche métier et la couche persistante.
    public UserDTO entityMappingToDto(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(userEntity.getId());
        userDTO.setCreatedAt(userEntity.getCreatedAt());
        userDTO.setUpdatedAt(userEntity.getUpdatedAt());
        userDTO.setRole(userEntity.getRole());
        userDTO.setPseudo(userEntity.getPseudo());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        return userDTO;
    }

    public UserEntity dtoMappingToEntity(UserDTO userDTO){
       UserEntity userEntity = new UserEntity();

        userEntity.setId(userDTO.getId());
        userEntity.setCreatedAt(userDTO.getCreatedAt());
        userEntity.setUpdatedAt(userDTO.getUpdatedAt());
        userEntity.setRole(userDTO.getRole());
        userEntity.setPseudo(userDTO.getPseudo());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        return userEntity;
    }
}