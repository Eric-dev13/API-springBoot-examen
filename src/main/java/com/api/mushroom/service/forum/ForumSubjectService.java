package com.api.mushroom.service.forum;


import com.api.mushroom.entity.ForumCategoryEntity;
import com.api.mushroom.entity.ForumSubjectEntity;
import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.repository.ForumCategoryJpaRepository;
import com.api.mushroom.repository.ForumSubjectJpaRepository;
import com.api.mushroom.service.forum.mapper.ForumCategoryServiceMapper;
import com.api.mushroom.service.forum.mapper.ForumSubjectServiceMapper;
import com.api.mushroom.service.forum.model.ForumCategoryServiceModel;
import com.api.mushroom.service.forum.model.ForumSubjectServiceModel;
import com.api.mushroom.service.forum.model.ForumUserServiceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForumSubjectService {

    private final ForumSubjectJpaRepository forumSubjectJpaRepository;
    private final ForumCategoryJpaRepository forumCategoryJpaRepository;

    private final ForumSubjectServiceMapper forumSubjectServiceMapper;
    private final ForumCategoryServiceMapper forumCategoryServiceMapper;

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
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();

        if(userEntity != null) {
            // Mapping
            ForumSubjectEntity forumSubjectEntity = forumSubjectServiceMapper.forumSubjectServiceModelToForumSubjectEntity(forumSubjectServiceModel);

            // Ajoute la liste de catégorie
                for(Long id : forumSubjectServiceModel.getCategoriesId()){
                    if(id > -1) {
                        ForumCategoryEntity forumCategoryEntity = forumCategoryJpaRepository.findById(id).orElse(null);
                        forumSubjectEntity.getForumCategories().add(forumCategoryEntity);
                        forumCategoryEntity.getForumSubjects().add(forumSubjectEntity);
                    }
                }

            // Lie le sujet nouvellement posté avec l'utilisateur courant
            forumSubjectEntity.setUser(userEntity);

            ForumSubjectEntity savedForumSubject = forumSubjectJpaRepository.save(forumSubjectEntity);
            return savedForumSubject != null;
        }
        return false;
    }

    public boolean addNew(ForumSubjectServiceModel forumSubjectServiceModel) {
        List <Optional<ForumCategoryEntity>> forumCategoryEntity = forumSubjectServiceModel.getCategoriesId().stream().map(forumCategoryJpaRepository::findById).toList();

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
