package com.api.mushroom.service.forum;


import com.api.mushroom.entity.ForumCommentaryEntity;
import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.repository.ForumCommentaryJpaRepository;
import com.api.mushroom.service.forum.mapper.ForumCommentaryServiceMapper;
import com.api.mushroom.service.forum.model.ForumCommentaryServiceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForumCommentaryService {

    private final ForumCommentaryJpaRepository forumCommentaryJpaRepository;
    private final ForumCommentaryServiceMapper forumCommentaryServiceMapper;

    public boolean add(ForumCommentaryServiceModel forumCommentaryServiceModel) {

        // Récupérer l'email de l'utilisateur courant
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();

        ForumCommentaryEntity forumCommentaryEntity = forumCommentaryServiceMapper.addForumCategoryServiceModelToForumCategoryEntity(forumCommentaryServiceModel);

        forumCommentaryEntity.setUser(userEntity);

        ForumCommentaryEntity returnForumCommentaryEntity = forumCommentaryJpaRepository.save(forumCommentaryEntity);

        return returnForumCommentaryEntity != null;

    }
}
