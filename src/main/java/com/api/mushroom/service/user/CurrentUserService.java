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

    private final UserServiceMapper userServiceMapper;

    public UserServiceModel getCurrentUser(String email) {

        Optional<UserEntity> user = userEntityJpaRepository.findByEmail(email);

        if(user.isPresent()) {
            // MAPPAGE AVEC MapStruct
            return userServiceMapper.userEntityToUserServiceModel(user.get());
        }
        // L'Optional est vide, l'utilisateur n'a pas été trouvé
        return null;
    }

    public boolean updateCurrentUser(UserServiceModel userServiceModel) {

        // Récupérer l'email de l'utilisateur courant
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Optional<UserEntity> userEntity = userEntityJpaRepository.findByEmail(email);

        if (userEntity.isPresent()) {
            UserEntity currentUser = userEntity.get();
            currentUser.setPseudo(userServiceModel.pseudo());

            currentUser.setLastname(userServiceModel.lastname());
            currentUser.setFirstname(userServiceModel.firstname());

            if (!userServiceModel.filename().isBlank()) {
                currentUser.setFilename(userServiceModel.filename());
            }

            UserEntity user = userEntityJpaRepository.save(currentUser);
            // Vérifiez si l'objet enregistré n'est pas nul pour déterminer si l'enregistrement a réussi.
            return user != null;
        }

        // L'Optional est vide, l'utilisateur n'a pas été trouvé
        return false;

    }
}
