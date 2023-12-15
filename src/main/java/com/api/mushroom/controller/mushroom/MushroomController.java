package com.api.mushroom.controller.mushroom;


import com.api.mushroom.Mapper.MapStructMapper;
import com.api.mushroom.controller.dto.MushroomDto;
import com.api.mushroom.controller.dto.MushroomsPaginateDto;
import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.repository.MushroomJpaRepository;
import com.api.mushroom.service.MushroomService;
import com.api.mushroom.service.model.MushroomServiceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/mushroom")
public class MushroomController {

    private final MapStructMapper mapStructMapper;
    private final MushroomService mushroomService;


    @GetMapping()
    public ResponseEntity<MushroomsPaginateDto> findAllFilter( @RequestParam(name = "limit", required = false) Long limit, @RequestParam(name = "offset", required = false) Long offset ) {
        try {
            List<MushroomServiceModel> mushroomServiceModels = mushroomService.findAllFilter(limit, offset);
            List<MushroomDto> mushroomDtos = mushroomServiceModels.stream().map(mapStructMapper::mushroomServiceMushroomDto).collect(Collectors.toList());

            // Compter le nombre total de champignons visibles
            Long totalMushrooms = mushroomService.countAllVisibleMushrooms();

            // Créer un objet DTO pour les champignons paginés
            MushroomsPaginateDto mushroomsPaginate = new MushroomsPaginateDto(
                    mushroomDtos,
                    totalMushrooms
            );

            // Retourner la réponse avec le code HTTP 200 (OK)
            return ResponseEntity.ok(mushroomsPaginate);
        } catch (Exception e) {
            // Si une exception est levée, renvoyer une réponse 404
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MushroomDto> getById(@PathVariable("id") Long id) {
        try {
            MushroomServiceModel mushroom = mushroomService.getById(id);
            return ResponseEntity.ok(mapStructMapper.mushroomServiceMushroomDto(mushroom));
        } catch (RoleNotFoundException e) {
            // Gérer l'erreur lorsque le rôle n'est pas trouvé
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findBySlug/{slug}")
    public MushroomDto findBySlug(@PathVariable("slug") String slug) {
        MushroomServiceModel mushroom = mushroomService.findBySlug(slug);
        return mapStructMapper.mushroomServiceMushroomDto(mushroom);
    }
}
