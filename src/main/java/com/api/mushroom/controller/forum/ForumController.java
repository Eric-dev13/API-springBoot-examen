package com.api.mushroom.controller.forum;

import com.api.mushroom.controller.forum.dto.ForumSubjectDto;
import com.api.mushroom.controller.forum.dto.ForumSubjectPaginatorDto;
import com.api.mushroom.controller.forum.mapper.ForumSubjectEntityMapper;
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

            // Vérifie si les paramètres limit et offset sont fournis
            if (limit == null) {
                // Si non, récupère toutes les entités (peut être lourd si beaucoup d'entités)
                forumSubjectEntities = forumService.findAll();
            } else {
                // Si oui, récupère les entités paginées
                forumSubjectEntities = forumService.findAllPaginate(limit, offset);
            }

            // Mapping des entités vers des DTO
            List<ForumSubjectDto> forumSubjectDtos = forumSubjectEntities.stream().map(forumSubjectEntityMapper::toDto).collect(Collectors.toList());

            // Compter le nombre total de sujets de forum
            Long totalForumSubject = forumService.countAllForumSubject();

            // Créer un objet DTO pour les sujets de forum paginés
            ForumSubjectPaginatorDto forumSubjectsPaginate = new ForumSubjectPaginatorDto(
                    forumSubjectDtos,
                    totalForumSubject
            );

            return ResponseEntity.ok(forumSubjectsPaginate);

        } catch (Exception e) {
            // Si une exception est levée, renvoyer une réponse 404
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
