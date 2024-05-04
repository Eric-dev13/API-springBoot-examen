package com.api.mushroom.controller.forum;

import com.api.mushroom.Mapper.MapStructMapper;
import com.api.mushroom.controller.dto.ForumCommentaryDto;
import com.api.mushroom.service.model.ForumCommentaryServiceModel;
import com.api.mushroom.service.forum.ForumCommentaryService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/forum/commentary")
public class ForumCommentaryController {

    private final ForumCommentaryService forumCommentaryService;
    private final MapStructMapper mapStructMapper;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public boolean add(@RequestBody ForumCommentaryDto forumCommentaryDto) {
        ForumCommentaryServiceModel forumCommentaryServiceModel = mapStructMapper.forumCommentaryDtoToServiceModel(forumCommentaryDto);
        return forumCommentaryService.add(forumCommentaryServiceModel);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public boolean put(@PathVariable("id") Long commentaryId, @RequestBody ForumCommentaryDto forumCommentaryDto) {
        //Mapping
        ForumCommentaryServiceModel forumCommentaryServiceModel = mapStructMapper.forumCommentaryDtoToServiceModel(forumCommentaryDto);
        return forumCommentaryService.put(commentaryId, forumCommentaryServiceModel);
    }
}
