package com.api.mushroom.service.forum;


import com.api.mushroom.entity.ForumSubjectEntity;
import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.repository.ForumSubjectJpaRepository;
import com.api.mushroom.service.forum.mapper.ForumSubjectServiceMapper;
import com.api.mushroom.service.forum.model.ForumSubjectServiceModel;
import com.api.mushroom.service.forum.model.ForumUserServiceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumSubjectService {

    private final ForumSubjectJpaRepository forumSubjectJpaRepository;
    private final ForumSubjectServiceMapper forumSubjectServiceMapper;

    /* --------------------------------------------------------------- */
    /*                          ROUTE - PUBLIQUE                       */
    /* --------------------------------------------------------------- */
    public List<ForumSubjectEntity> findAll() {
        return forumSubjectJpaRepository.findAll(Sort.by(Sort.Order.asc("createdAt")));
    }

    public List<ForumSubjectEntity> findAllPaginate(Long limit, Long offset) {
        return forumSubjectJpaRepository.findAllPaginate(limit, offset);
    }

    public Long countAllForumSubject() {
        return forumSubjectJpaRepository.countAllForumSubject();
    }

    public List<ForumSubjectEntity> findPaginateAndFilterCategory(Long limit, Long offset, Long categoryId) {
        return forumSubjectJpaRepository.findPaginateAndFilterCategory(limit, offset, categoryId);
    }

    public Long countTotalForumSubjectsByCategory(Long categoryId) {
        return forumSubjectJpaRepository.countTotalForumSubjectsByCategory(categoryId);
    }

    public ForumSubjectEntity findById(Long id) {
        return forumSubjectJpaRepository.findById(id).orElse(null);
    }



    public boolean add(ForumSubjectServiceModel forumSubjectServiceModel) {
        // Récupérer l'email de l'utilisateur courant
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();

        if(user != null) {
            // Mapping
            ForumUserServiceModel forumUserServiceModel = forumSubjectServiceMapper.userEntityToForumUserServiceModel(user);

            // Lie le sujet nouvellement posté avec l'utilisateur courant
            forumSubjectServiceModel.setUser(forumUserServiceModel);

            // Mapping
            ForumSubjectEntity forumSubjectEntity = forumSubjectServiceMapper.forumSubjectServiceModelToForumSubjectEntity(forumSubjectServiceModel);

            ForumSubjectEntity savedForumSubject = forumSubjectJpaRepository.save(forumSubjectEntity);
            return savedForumSubject != null;
        }
        return false;
    }

/*    public boolean add(ForumSubjectEntity forumSubjectEntity) {
        ForumSubjectEntity savedForumSubject = forumSubjectJpaRepository.save(forumSubjectEntity);
        return savedForumSubject != null;
    }*/

    /* --------------------------------------------------------------- */
    /*                          ROUTE - SECURISER                      */
    /* --------------------------------------------------------------- */





    /* --------------------------------------------------------------- */
    /*                    MAPPER SERVICE MODEL ABANDONNE               */
    /* --------------------------------------------------------------- */

/*    public List<ForumSubjectServiceModel> findAll() {
        List<ForumSubjectEntity> forumSubjectEntities = forumSubjectJpaRepository.findAll();
        return forumSubjectEntities.stream().map(forumSubjectServiceMapper::forumSubjectEntityToForumSubjectServiceModel).collect(Collectors.toList());
    }*/

/*    public List<ForumSubjectServiceModel> findAllPaginate(Long limit, Long offset) {
        List<ForumSubjectEntity> forumSubjectEntities = forumSubjectJpaRepository.findPaginate(limit, offset);
        return forumSubjectEntities.stream().map(forumSubjectServiceMapper::forumSubjectEntityToForumSubjectServiceModel).collect(Collectors.toList());
    }*/

/*    public ForumSubjectServiceModel findById(Long id) {
       ForumSubjectEntity forumSubject = forumSubjectJpaRepository.findById(id).orElse(null);
        return forumSubjectServiceMapper.forumSubjectEntityToForumSubjectServiceModel(forumSubject);
    }*/

/*    public boolean add(ForumSubjectEntity forumSubjectEntity) {

        // Récupérer l'email de l'utilisateur courant
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Récupérer l'objet User
        UserEntity principal = (UserEntity) authentication.getPrincipal();


        ForumSubjectEntity savedForumSubject = forumSubjectJpaRepository.save(forumSubjectEntity);
        return savedForumSubject != null;
    }*/


}
