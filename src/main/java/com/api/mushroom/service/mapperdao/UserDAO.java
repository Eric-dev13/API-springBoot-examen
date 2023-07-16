package com.api.mushroom.service.mapperdao;

import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDAO {
    //private final UserDTO userDTO;

    // Le pattern DAO (Data Access Object) permet de faire le lien entre la couche métier et la couche persistante.
    public UserDTO entityMappingToDto(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(userEntity.getId());
        userDTO.setCreatedAt(userEntity.getCreatedAt());
        userDTO.setUpdatedAt(userEntity.getUpdatedAt());
        //userDTO.setRole(userEntity.getRole());
        userDTO.setPseudo(userEntity.getPseudo());
        userDTO.setLastName(userEntity.getLastname());
        userDTO.setFirstName(userEntity.getFirstname());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        return userDTO;
    }

    public UserEntity dtoMappingToEntity(UserDTO userDTO){
       UserEntity userEntity = new UserEntity();

        userEntity.setId(userDTO.getId());
        userEntity.setCreatedAt(userDTO.getCreatedAt());
        userEntity.setUpdatedAt(userDTO.getUpdatedAt());
        //userEntity.setRole(userDTO.getRole());
        userEntity.setPseudo(userDTO.getPseudo());
        userEntity.setLastname(userDTO.getLastName());
        userEntity.setFirstname(userDTO.getFirstName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        return userEntity;
    }
}