package com.api.mushroom.service.user;

import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.repository.UserEntityJpaRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Data
@RequiredArgsConstructor
public class UserService {

    // Via l'annotation @RequiredArgsConstructor Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    // DAO : Data Acces Object permet de communiquer avec la DB
    private final UserEntityJpaRepository userEntityJpaRepository;


    // GET - Récupère un tableau d'enregistrement
    public Iterable<UserEntity> getAll(){
        return userEntityJpaRepository.findAll();
    }

    // GET - Récupère un enregistrement par l'ID
    public Optional<UserEntity> getById(Long id){
        return userEntityJpaRepository.findById(id);
    }

    // POST : Ajouter un enregistrement
    public UserEntity add(UserEntity userEntity){
        return userEntityJpaRepository.save(userEntity);
    }

    // UPDATE : Mettre à jour un enregistrement
    public UserEntity edit(UserEntity userEntity){
        return userEntityJpaRepository.save(userEntity);
    }

    // delete : Supprimer un enregistrement
    public void delete(Long id) {
        userEntityJpaRepository.deleteById(id);
    }

}
