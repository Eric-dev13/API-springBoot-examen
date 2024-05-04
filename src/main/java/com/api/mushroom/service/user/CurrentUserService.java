package com.api.mushroom.service.user;


import com.api.mushroom.Mapper.MapStructMapper;
import com.api.mushroom.service.model.*;

import com.api.mushroom.entity.ForumSubjectEntity;
import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.repository.ForumSubjectJpaRepository;
import com.api.mushroom.repository.UserEntityJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CurrentUserService {

    private final ForumSubjectJpaRepository forumSubjectJpaRepository;
    private final UserEntityJpaRepository userEntityJpaRepository;

    private final PasswordEncoder passwordEncoder;

    private final MapStructMapper mapStructMapper;


    public UserServiceModel getCurrentUser() {
        // Récupérer l'email de l'utilisateur courant
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Optional<UserEntity> user = userEntityJpaRepository.findByEmail(email);

        if(user.isPresent()) {
            // MAPPAGE AVEC MapStruct
            return mapStructMapper.userEntityToUserServiceModel(user.get());
        }
        // L'Optional est vide, l'utilisateur n'a pas été trouvé
        // TODO: Remonter une exception dans updateCurrentUser
        return null;
    }

    public  List<ForumSubjectServiceModel> getFullCurrentUser() {
        // Récupérer l'email de l'utilisateur courant
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Optional<UserEntity> userEntity = userEntityJpaRepository.findByEmail(email);

        if(userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            List<ForumSubjectEntity> forumSubjectEntities = forumSubjectJpaRepository.findAllSubjectsByUser(user.getId());
            return forumSubjectEntities.stream().map(mapStructMapper::forumSubjectEntityToForumSubjectService).collect(Collectors.toList());
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

            currentUser.setPseudo(userServiceModel.getPseudo());currentUser.setLastname(userServiceModel.getLastname());
            currentUser.setFirstname(userServiceModel.getFirstname());

            if (!userServiceModel.getFilename().isBlank()) {
                // Supprimer l'ancien fichier si il existe, récupérer le répertoire public users
                    Path publicDirectory = Paths.get(".", "public/upload/users").toAbsolutePath();
                    File file = new File(publicDirectory + "/" + currentUser.getFilename());
                    if(file.exists()){
                        boolean success = file.delete();
                    }
                // Ajoute le nouveau nom
                currentUser.setFilename(userServiceModel.getFilename());
            }
            UserEntity user = userEntityJpaRepository.save(currentUser);
            return mapStructMapper.userEntityToUserServiceModel(user);
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
            String password = changePasswordServiceModel.getPassword(); // Password entered by user
            String passwordBd = currentUser.getPassword(); // Load hashed DB password

            // Comparaison du password remonté avec celui stocké en base de données
            boolean isValid = passwordEncoder.matches(password, passwordBd);

            // Si les passwords sont identiques, j'actualise le mot de passe dans la base de donnée"
            if(isValid) {
                currentUser.setPassword(passwordEncoder.encode(changePasswordServiceModel.getNewPassword()));
                userEntityJpaRepository.save(currentUser);
                return true;
            }
        }
        return false;
    }

//    public ForumSubjectDto findAllSubjects() {
//        // Récupérer l'email de l'utilisateur courant
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//
//        // Récupèrer l'utilisateur courrant
//        Optional<UserEntity> userEntity = userEntityJpaRepository.findByEmail(email);
//
//        if (userEntity.isPresent()) {
//            UserEntity currentUser = userEntity.get();
//            Long user_id = currentUser.getId();
//            currentUser
//    }
}
