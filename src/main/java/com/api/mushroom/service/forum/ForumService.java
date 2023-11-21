package com.api.mushroom.service.forum;


import com.api.mushroom.entity.ForumSubjectEntity;
import com.api.mushroom.repository.ForumSubjectJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumService {

    private final ForumSubjectJpaRepository forumSubjectJpaRepository;

    /* --------------------------------------------------------------- */
    /*                          ROUTE - PUBLIQUE                       */
    /* --------------------------------------------------------------- */
    public List<ForumSubjectEntity> findAll() {
        return forumSubjectJpaRepository.findAll();
    }

    public List<ForumSubjectEntity> findAllPaginate(Long limit, Long offset) {
        return forumSubjectJpaRepository.findPaginate(limit, offset);
    }

    public ForumSubjectEntity findById(Long id) {
        return forumSubjectJpaRepository.findById(id).orElse(null);
    }

    public Long countAllVisibleMushrooms() {
        return forumSubjectJpaRepository.countAllVisibleMushrooms();
    }





    /* --------------------------------------------------------------- */
    /*                          ROUTE - SECURISER                      */
    /* --------------------------------------------------------------- */
}
