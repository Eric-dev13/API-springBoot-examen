package com.api.mushroom.controller.forum;

import com.api.mushroom.controller.forum.dto.ForumCommentaryDto;
import com.api.mushroom.controller.forum.mapper.ForumDtoMapper;
import com.api.mushroom.service.forum.ForumCommentaryService;
import com.api.mushroom.service.forum.model.ForumCommentaryServiceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/forum/commentary")
public class ForumCommentaryController {

    private final ForumCommentaryService forumCommentaryService;
    private final ForumDtoMapper forumDtoMapper;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public boolean add(@RequestBody ForumCommentaryDto commentaryDto) {
        ForumCommentaryServiceModel forumCommentaryServiceModel = forumDtoMapper.forumCommentaryDtoToForumCommentaryServiceModel(commentaryDto);
        return forumCommentaryService.add(forumCommentaryServiceModel);
    }
}
