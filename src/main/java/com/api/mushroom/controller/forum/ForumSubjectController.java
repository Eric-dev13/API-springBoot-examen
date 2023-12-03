package com.api.mushroom.controller.forum;

import com.api.mushroom.controller.forum.dto.ForumSubjectAddDto;
import com.api.mushroom.controller.forum.dto.ForumSubjectDto;
import com.api.mushroom.controller.forum.dto.ForumSubjectPaginatorDto;
import com.api.mushroom.controller.forum.mapper.ForumSubjectDtoMapper;
import com.api.mushroom.entity.ForumSubjectEntity;
import com.api.mushroom.service.forum.ForumSubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/forum")
public class ForumSubjectController {

    private final ForumSubjectService forumSubjectService;

    private final ForumSubjectDtoMapper forumSubjectDtoMapper;

    @GetMapping
    public ResponseEntity<ForumSubjectPaginatorDto> findAllPaginate(
            @RequestParam(name = "limit", required = false) Long limit,
            @RequestParam(name = "offset", required = false) Long offset,
            @RequestParam(name = "category", required = false) Long categoryId
    ) throws Exception {

        try {
            List<ForumSubjectEntity> forumSubjectEntities;

            // Compter le nombre total de sujets de forum
            Long totalForumSubject;

            // Vérifie si category, limit et offset sont fournis
            if ( limit != null && offset != null && categoryId != null) {
                // Si oui, récupère les entités paginées avec filtre par catégorie
                forumSubjectEntities = forumSubjectService.findPaginateAndFilterCategory(limit, offset, categoryId);
                totalForumSubject = forumSubjectService.countTotalForumSubjectsByCategory(categoryId);
            } else if (limit != null && offset != null) {
                // Si seulement limit et offset sont fournis, récupère les entités paginées sans filtre de catégorie
                forumSubjectEntities = forumSubjectService.findAllPaginate(limit, offset);
                totalForumSubject = forumSubjectService.countAllForumSubject();
            } else {
                // Si aucun paramètre n'est fourni, récupère toutes les entités (peut être lourd si beaucoup d'entités)
                forumSubjectEntities = forumSubjectService.findAll();
                totalForumSubject = forumSubjectService.countAllForumSubject();
            }

            // Mapping des entités vers des DTO
            List<ForumSubjectDto> forumSubjectDtos = forumSubjectEntities.stream().map(forumSubjectDtoMapper::forumSubjectEntityToForumSubjectDto).collect(Collectors.toList());


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

    public ResponseEntity<ForumSubjectDto> findById(@PathVariable Long id) {

        // Une seule couche de mapping DTO --> ENTITY gestion
        ForumSubjectEntity forumSubjectEntity = forumSubjectService.findById(id);

        if (forumSubjectEntity != null) {
            ForumSubjectDto forumGetDto = forumSubjectDtoMapper.forumSubjectEntityToForumSubjectDto(forumSubjectEntity);
            return new ResponseEntity<>(forumGetDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public boolean add(@Valid @RequestBody ForumSubjectAddDto forumSubjectAddDto) {
        return forumSubjectService.add(forumSubjectDtoMapper.forumSubjectAddDtoToForumSubjectServiceModel(forumSubjectAddDto));
    }


    /* --------------------------------------------------------------- */
    /*                    MAPPER SERVICE MODEL ABANDONNE               */
    /* --------------------------------------------------------------- */

/*    @GetMapping
    public ResponseEntity<ForumSubjectPaginatorDto> findAllPaginate(
            @RequestParam(name = "limit", required = false) Long limit,
            @RequestParam(name = "offset", required = false) Long offset
    ) throws Exception {

        try {
            List<ForumSubjectServiceModel> forumSubjectServiceModels;

            // Vérifie si les paramètres limit et offset sont fournis
            if (limit == null) {
                // Si non, récupère toutes les entités (peut être lourd si beaucoup d'entités)
                forumSubjectServiceModels = forumService.findAll();
            } else {
                // Si oui, récupère les entités paginées
                forumSubjectServiceModels = forumService.findAllPaginate(limit, offset);
            }

            // Mapping des entités vers des DTO
            List<ForumSubjectDto> forumSubjectDtos = forumSubjectServiceModels.stream().map(forumSubjectDtoMapper::forumSubjectServiceModelToForumSubjectDto).collect(Collectors.toList());

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
    }*/

/*    @GetMapping("/{id}")
    public ResponseEntity<ForumSubjectDto> findById(@PathVariable Long id) {


        ForumSubjectServiceModel forumSubjectServiceModel = forumService.findById(id);

        if (forumSubjectServiceModel != null) {
            ForumSubjectDto forumGetDto = forumSubjectDtoMapper.forumSubjectServiceModelToForumSubjectDto(forumSubjectServiceModel);
            return new ResponseEntity<>(forumGetDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

/*    @PostMapping
    public boolean add(@Valid @RequestBody ForumSubjectAddDto forumSubjectAddDto) {
        return forumService.add(forumSubjectDtoMapper.forumSubjectAddDtoToEntity(forumSubjectAddDto));
    }*/
}
