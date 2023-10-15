package com.api.mushroom.service.user;

import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.repository.UserEntityJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CurrentUserService {

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

    public boolean updateCurrentUser(UserServiceModel userServiceModel) {

        // Récupérer l'email de l'utilisateur courant
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Optional<UserEntity> userEntity = userEntityJpaRepository.findByEmail(email);

        if (userEntity.isPresent()) {
            UserEntity currentUser = userEntity.get();
            currentUser.setPseudo(userServiceModel.getPseudo());

            currentUser.setLastname(userServiceModel.getLastname());
            currentUser.setFirstname(userServiceModel.getFirstname());

            if (userServiceModel.getFilename().isPresent()) {
                currentUser.setFilename(userServiceModel.getFilename().get());
            }

            UserEntity user = userEntityJpaRepository.save(currentUser);
            // Vérifiez si l'objet enregistré n'est pas nul pour déterminer si l'enregistrement a réussi.
            return user != null;
        }

        // L'Optional est vide, l'utilisateur n'a pas été trouvé
        return false;

    }
}
