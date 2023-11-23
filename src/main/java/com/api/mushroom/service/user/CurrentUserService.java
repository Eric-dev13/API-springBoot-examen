package com.api.mushroom.service.user;

import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.repository.UserEntityJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CurrentUserService {

    private final UserEntityJpaRepository userEntityJpaRepository;
    private final UserServiceMapper userServiceMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceModel getCurrentUser(String email) {

        Optional<UserEntity> user = userEntityJpaRepository.findByEmail(email);

        if(user.isPresent()) {
            // MAPPAGE AVEC MapStruct
            return userServiceMapper.userEntityToUserServiceModel(user.get());
        }
        // L'Optional est vide, l'utilisateur n'a pas été trouvé
        // TODO: Remonter une exception dans updateCurrentUser
        return null;
    }

    public UserServiceModel updateCurrentUser(UserServiceModel userServiceModel) {

        // Récupérer l'email de l'utilisateur courant
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Récupèrer l'utilisateur courrant
        Optional<UserEntity> userEntity = userEntityJpaRepository.findByEmail(email);

        if (userEntity.isPresent()) {
            UserEntity currentUser = userEntity.get();

            currentUser.setPseudo(userServiceModel.pseudo());
            currentUser.setLastname(userServiceModel.lastname());
            currentUser.setFirstname(userServiceModel.firstname());

            if (!userServiceModel.filename().isBlank()) {
                // supprimer l'ancien fichier si il existe
                if(!currentUser.getFilename().isEmpty()){
                    // Récupérer le répertoire public users
                    Path publicDirectory = Paths.get(".", "public/upload/users").toAbsolutePath();
                    File file = new File(publicDirectory + "/" + currentUser.getFilename());
                    if(file.exists()){
                        boolean success = file.delete();
                    }
                }
                // Ajoute le nouveau nom
                currentUser.setFilename(userServiceModel.filename());
            }
            UserEntity user = userEntityJpaRepository.save(currentUser);
            return userServiceMapper.userEntityToUserServiceModel(user);
        }
        // l'utilisateur n'a pas été trouvé
        return null;
    }

    public boolean updatePassword(ChangePasswordServiceModel changePasswordServiceModel) {
        // vérifier le mot de passe

        // enregistrer le nouveau mot de passe

        // Récupérer l'email de l'utilisateur courant
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Récupèrer l'utilisateur courant
        Optional<UserEntity> userEntity = userEntityJpaRepository.findByEmail(email);

        if (userEntity.isPresent()) {
            UserEntity currentUser = userEntity.get();
            // Vérifier le mot de passe.
            // Encode du password remonté
            String password = changePasswordServiceModel.password(); // Password entered by user
            String passwordBd = currentUser.getPassword(); // Load hashed DB password

            // Comparaison du password remonté avec celui stocké en base de données
            boolean isValid = passwordEncoder.matches(password, passwordBd);

            // Si les passwords sont identiques, j'actualise le mot de passe dans la base de donnée"
            if(isValid) {
                currentUser.setPassword(passwordEncoder.encode(changePasswordServiceModel.newPassword()));
                userEntityJpaRepository.save(currentUser);
                return true;
            }
        }
        return false;
    }
}
