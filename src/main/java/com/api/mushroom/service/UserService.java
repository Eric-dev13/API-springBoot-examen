package com.api.mushroom.service;

import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.repository.UserEntityJpaRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
public class UserService {

    // Via l'annotation @RequiredArgsConstructor Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    private final UserEntityJpaRepository userEntityJpaRepository;
    // private final UserRepository userRepository;

    // GET - Récupère un tableau d'enregistrement
    public Iterable<UserEntity> getAll(){
        return userEntityJpaRepository.findAll();
    }

    // GET - Récupère un enregistrement par l'ID
    public Optional<UserEntity> getById(Long id){
        return userEntityJpaRepository.findById(id);
    }

    // POST : Ajouter un enregistrement
    public UserEntity add(@RequestBody UserEntity userEntity){
        return userEntityJpaRepository.save(userEntity);
    }

    // UPDATE : Mettre à jour un enregistrement
    public UserEntity edit(@RequestBody UserEntity userEntity){
        return userEntityJpaRepository.save(userEntity);
    }

    // delete : Supprimer un enregistrement
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
