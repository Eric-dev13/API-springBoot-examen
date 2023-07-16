package com.api.mushroom.service;

import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.repository.UserEntityJpaRepository;
import com.api.mushroom.repository.UserRepository;
import com.api.mushroom.service.dto.UserDTO;
import com.api.mushroom.service.mapperdao.UserDAO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
public class UserService {

    // Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    private final UserEntityJpaRepository userEntityJpaRepository;
    final UserRepository userRepository;

    public Iterable<UserEntity> getAll(){
        return userRepository.findAll();
    }

    // Récupère une entité par son identifiant
    public Optional<UserEntity> getById(Long id){
        return userEntityJpaRepository.findById(id);
    }

    // UPDATE : Mettre à jour un compte utilisateur
    public UserEntity add(@RequestBody UserEntity userEntity){
        return userEntityJpaRepository.save(userEntity);
    }

    public UserEntity edit(@RequestBody UserEntity userEntity){
        return userEntityJpaRepository.save(userEntity);
    }

    public void delete(Long id) {
        userEntityJpaRepository.deleteById(id);
    }

    /*

    public UserDTO getById(Long userId){
        UserDAO userDAO = new UserDAO();
        Optional <UserEntity> optionalUserEntity = userRepository.findById(userId);
        return userDAO.entityMappingToDto(optionalUserEntity.get());
    }

        public UserEntity addUser(@RequestBody UserDTO userDTO) {
        UserDAO userDAO = new UserDAO();
        UserEntity userEntity = userDAO.dtoMappingToEntity(userDTO);
        return userRepository.save(userEntity);
    }

    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }

     */

}
