package com.api.mushroom.service.user;

import com.api.mushroom.controller.user.UserDTO;
import com.api.mushroom.controller.user.UserGetDTO;
import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.repository.UserEntityJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserEntityJpaRepository userEntityJpaRepository;

    public UserServiceModel getCurrentUser(String email) {
        Optional<UserEntity> user = userEntityJpaRepository.findByEmail(email);
        if(user.isEmpty()) {
            // L'Optional est vide, l'utilisateur n'a pas été trouvé
            return null;
        }

        // MAPPAGE AVEC MapStruct
        return UserServiceMapper.INSTANCE.userEntityToUserServiceMapper(user.get());
    }

    public UserServiceModel update(Long id, UserServiceModel userServiceModel) {

        Optional<UserEntity> userEntity = userEntityJpaRepository.findById(id);

        if (userEntity.isEmpty()) {
            // L'Optional est vide, l'utilisateur n'a pas été trouvé
            return null;
        }



        //userEntityJpaRepository.save();
        // MAPPAGE AVEC MapStruct
        //return UserServiceMapper.INSTANCE.userEntityToUserServiceMapper(user.get());
        return null;
    }
}
