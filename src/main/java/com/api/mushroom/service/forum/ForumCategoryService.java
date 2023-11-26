package com.api.mushroom.service.forum;

import com.api.mushroom.controller.forum.dto.ForumCategoryDto;
import com.api.mushroom.entity.ForumCategoryEntity;
import com.api.mushroom.repository.ForumCategoryJpaRepository;
import com.api.mushroom.service.forum.mapper.ForumCategoryServiceMapper;
import com.api.mushroom.service.forum.model.ForumCategoryServiceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForumCategoryService {

    private final ForumCategoryJpaRepository forumCategoryJpaRepository;
    private final ForumCategoryServiceMapper forumCategoryServiceMapper;

    public List<ForumCategoryServiceModel> findAll() {
        List<ForumCategoryEntity> forumCategoryEntity = forumCategoryJpaRepository.findAll(Sort.by(Sort.Order.asc("name")));
        return forumCategoryEntity.stream().map(forumCategoryServiceMapper::forumCategoryEntityToForumCategoryServiceModel).collect(Collectors.toList());
    }
}
