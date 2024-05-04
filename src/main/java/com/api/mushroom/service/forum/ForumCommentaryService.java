package com.api.mushroom.service.forum;


import com.api.mushroom.entity.ForumCommentaryEntity;
import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.exception.UserMismatchException;
import com.api.mushroom.repository.ForumCommentaryJpaRepository;
import com.api.mushroom.service.model.ForumCommentaryServiceModel;
import com.api.mushroom.Mapper.MapStructMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ForumCommentaryService {

    private final ForumCommentaryJpaRepository forumCommentaryJpaRepository;
    private final MapStructMapper mapStructMapper;

    public boolean add(ForumCommentaryServiceModel forumCommentaryServiceModel) {

        // Récupérer l'email de l'utilisateur courant.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();

        // Je vérifie que l'utilisateur associé au commentaire correspond à l'utilisateur authentifié.
        if(forumCommentaryServiceModel.getUser().getId() == userEntity.getId()) {
            System.out.println("OK, la personne authentifié est bien l'auteur du commentaire !");
            ForumCommentaryEntity forumCommentaryEntity = mapStructMapper.forumCommentaryServiceToForumCommentaryEntity(forumCommentaryServiceModel);

            forumCommentaryEntity.setUser(userEntity);

            ForumCommentaryEntity returnForumCommentaryEntity = forumCommentaryJpaRepository.save(forumCommentaryEntity);

            return returnForumCommentaryEntity != null;
        }
        throw new UserMismatchException("L'utilisateur associé au commentaire ne correspond pas à l'utilisateur authentifié.");

    }

    public boolean put(Long commentaryId, ForumCommentaryServiceModel forumCommentaryServiceModel) {

        // Récupérer l'email de l'utilisateur courant
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();

        // Je vérifie que l'utilisateur associé au commentaire correspond à l'utilisateur authentifié.
        if(forumCommentaryServiceModel.getUser().getId() == userEntity.getId()) {
            System.out.println("OK, la personne authentifié est bien l'auteur du commentaire !");

            //Mapping
            ForumCommentaryEntity forumCommentaryEntity = mapStructMapper.forumCommentaryServiceToForumCommentaryEntity(forumCommentaryServiceModel);

            // je recupère le commentaire
            ForumCommentaryEntity originalForumCommentaryEntity = forumCommentaryJpaRepository
                    .findById(commentaryId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            // Actualise le commentaire
            originalForumCommentaryEntity.setCommentary(forumCommentaryEntity.getCommentary());

            // Persistence
            ForumCommentaryEntity returnForumCommentaryEntity = forumCommentaryJpaRepository.save(originalForumCommentaryEntity);

            return returnForumCommentaryEntity != null;
        }
        throw new UserMismatchException("L'utilisateur associé au commentaire ne correspond pas à l'utilisateur authentifié.");

    }

//    public boolean put(Long user_id, ForumCommentaryServiceModel forumCommentaryServiceModel) {
//        // Récupérer l'email de l'utilisateur courant
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
//
//    }
}
