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
        List<ForumSubjectEntity> forumSubjectEntities =  forumSubjectJpaRepository.findAll();
        return forumSubjectEntities;
}

    public ForumSubjectEntity findById(Long id) {
        return forumSubjectJpaRepository.findById(id).orElse(null);
    }


    /* --------------------------------------------------------------- */
    /*                          ROUTE - SECURISER                      */
    /* --------------------------------------------------------------- */
}
