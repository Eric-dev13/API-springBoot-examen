package com.api.mushroom.controller.mushroom;


import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.repository.MushroomJpaRepository;
import com.api.mushroom.service.MushroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/mushroom")
public class MushroomController {

    // Via l'annotation @RequiredArgsConstructor Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    private final MushroomService mushroomService;

    private  final MushroomEntityMapper mushroomEntityMapper;

    private final MushroomJpaRepository mushroomJpaRepository;

    // GET - Liste des espèces validé par l'administrateur pour la publication.
//    @GetMapping
//    public List<MushroomGetDto> findAllByVisibility() {
//        List<MushroomEntity> mushroomEntities =  mushroomService.findAllByVisibility();
//        return mushroomEntities.stream().map(mushroomEntityMapper::toDto).collect(Collectors.toList());
//    }

    @GetMapping()
    public ResponseEntity<MushroomsPaginatorDto> findAllByVisibilityPaginate(
            @RequestParam(name = "limit", required = false)  Long limit,
            @RequestParam(name = "offset", required = false) Long offset
    ) {

        List<MushroomEntity> mushroomEntities;

        if (limit == null) {
            mushroomEntities =  mushroomService.findAllByVisibility();
        } else {
            mushroomEntities = mushroomService.findAllByVisibilityPaginate(limit, offset);
        }

        // Mapping
        List<MushroomGetDto> mushroomGetDtos = mushroomEntities.stream().map(mushroomEntityMapper::toDto).collect(Collectors.toList());

        // Count the total number of visible mushrooms
        Long totalMushrooms = mushroomService.countAllVisibleMushrooms();

        MushroomsPaginatorDto mushroomsPaginate = new MushroomsPaginatorDto(
            mushroomGetDtos,
            totalMushrooms
        );

        // Return the response with HTTP status code 200 (OK)
        return ResponseEntity.ok(mushroomsPaginate);
    }

    // GET - Affiche le descriptif d'une espèce
    @GetMapping("/{id}")
    public MushroomGetDto getById(@PathVariable("id") Long id) {
        MushroomEntity mushroom = mushroomService.getById(id).orElseThrow();
        return mushroomEntityMapper.toDto(mushroom);
    }

    @GetMapping("/findBySlug/{slug}")
    public MushroomGetDto findBySlug(@PathVariable("slug") String slug) {
        MushroomEntity mushroom = mushroomService.findBySlug(slug).orElseThrow();
        return mushroomEntityMapper.toDto(mushroom);
    }

}
