package com.api.mushroom.controller.forum;

import com.api.mushroom.controller.forum.dto.ForumSubjectDto;
import com.api.mushroom.controller.forum.dto.ForumSubjectPaginatorDto;
import com.api.mushroom.controller.forum.mapper.ForumSubjectEntityMapper;
import com.api.mushroom.entity.ForumSubjectEntity;
import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.service.forum.ForumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/forum")
public class ForumController {

    private final ForumService forumService;

    private final ForumSubjectEntityMapper forumSubjectEntityMapper;

//    @GetMapping
//    public List<ForumSubjectDto> findAll() {
//        return forumService.findAll().stream().map(forumSubjectEntityMapper::toDto).collect(Collectors.toList());
//    }

    @GetMapping
    public ResponseEntity<ForumSubjectPaginatorDto> findAllPaginate(
            @RequestParam(name = "limit", required = false) Long limit,
            @RequestParam(name = "offset", required = false) Long offset
    ) throws Exception {

        try {
            List<ForumSubjectEntity> forumSubjectEntities;

            if (limit == null) {
                forumSubjectEntities = forumService.findAll();
            } else {
                forumSubjectEntities = forumService.findAllPaginate(limit, offset);
            }

            // Mapping
            List<ForumSubjectDto> forumSubjectDtos = forumSubjectEntities.stream().map(forumSubjectEntityMapper::toDto).collect(Collectors.toList());

            // Count the total number of visible mushrooms
            Long totalForumSubject = forumService.countAllVisibleMushrooms();

            ForumSubjectPaginatorDto forumSubjectsPaginate = new ForumSubjectPaginatorDto(
                    forumSubjectDtos,
                    totalForumSubject
            );

            return ResponseEntity.ok(forumSubjectsPaginate);

        } catch (Exception e) {
            // En cas d'erreur lors de l'écriture du fichier, afficher la trace d'erreur
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForumSubjectDto> findById(@PathVariable Long id) {

        // Une seule couche de mapping DTO --> ENTITY gestion
        ForumSubjectEntity forumSubjectEntity = forumService.findById(id);

        if (forumSubjectEntity != null) {
            ForumSubjectDto forumGetDto = forumSubjectEntityMapper.toDto(forumSubjectEntity);
            return new ResponseEntity<>(forumGetDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
