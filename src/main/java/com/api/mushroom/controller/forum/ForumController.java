package com.api.mushroom.controller.forum;

import com.api.mushroom.entity.ForumSubjectEntity;
import com.api.mushroom.service.forum.ForumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/forum")
public class ForumController {

    private final ForumService forumService;
    private final ForumSubjectEntityMapper forumSubjectEntityMapper;


    @GetMapping
    public List<ForumSubjectDto> findAll() {
        return forumService.findAll().stream().map(forumSubjectEntityMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForumSubjectDto> findById(@PathVariable Long id) {

        // une seule couche de mapping dto --> entity gestion
        ForumSubjectEntity forumSubjectEntity = forumService.findById(id);
        if(forumSubjectEntity != null){
            ForumSubjectDto forumGetDto = forumSubjectEntityMapper.toDto(forumSubjectEntity);
            return new ResponseEntity<>(forumGetDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
