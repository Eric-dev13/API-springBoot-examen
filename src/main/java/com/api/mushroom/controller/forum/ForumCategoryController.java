package com.api.mushroom.controller.forum;

import com.api.mushroom.controller.forum.dto.ForumCategoryDto;
import com.api.mushroom.controller.forum.mapper.ForumCategoryDtoMapper;
import com.api.mushroom.repository.ForumCategoryJpaRepository;
import com.api.mushroom.service.forum.ForumCategoryService;
import com.api.mushroom.service.forum.model.ForumCategoryServiceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/forum/category")
public class ForumCategoryController {

    private final ForumCategoryService forumCategoryService;
    private final ForumCategoryDtoMapper forumCategoryDtoMapper;
    private final ForumCategoryJpaRepository forumCategoryJpaRepository;

    @GetMapping
    public List<ForumCategoryDto> findAll(){
        List<ForumCategoryServiceModel> forumCategoryServiceModels = forumCategoryService.findAll();
        return forumCategoryServiceModels.stream().map((forumCategoryDtoMapper::forumCategoryServiceModelToForumCategoryDto)).collect(Collectors.toList());
    }
}
