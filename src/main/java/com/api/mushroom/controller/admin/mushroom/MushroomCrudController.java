package com.api.mushroom.controller.admin.mushroom;

import com.api.mushroom.Mapper.MapStructMapper;
import com.api.mushroom.controller.dto.MushroomDto;
import com.api.mushroom.controller.dto.MushroomsPaginateDto;
import com.api.mushroom.service.MushroomService;
import com.api.mushroom.service.model.MushroomServiceModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin/mushroom")
public class MushroomCrudController {

    private final MushroomService mushroomService;
    private final MapStructMapper mapStructMapper;

    @GetMapping()
    public ResponseEntity<MushroomsPaginateDto> findAllFilter(
            @RequestParam(name = "limit", required = false) Long limit,
            @RequestParam(name = "offset", required = false) Long offset
    ) {
        try {
            List<MushroomServiceModel> mushroomServiceModels = mushroomService.findAllFilterAdmin(limit, offset);

            List<MushroomDto> mushroomDtos = mushroomServiceModels.stream().map(mapStructMapper::mushroomServiceMushroomDto).collect(Collectors.toList());

            // Count the total number of visible mushrooms
            Long totalMushrooms = mushroomService.countAllMushrooms();

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

    // GET - Affiche le descriptif d'une espèce
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

    @PostMapping
    public Long add(@Valid @RequestBody MushroomDto mushroomDto) {
        MushroomServiceModel mushroomServiceModel = mapStructMapper.mushroomDtoToMushroomService(mushroomDto);
        return mushroomService.add(mushroomServiceModel);
    }

    @PutMapping("/{id}")
    public long put(@PathVariable("id") Long id, @Valid @RequestBody MushroomDto mushroomDto) {
        MushroomServiceModel mushroomServiceModel = mapStructMapper.mushroomDtoToMushroomService(mushroomDto);
        return mushroomService.put(id, mushroomServiceModel);
    }

    @PutMapping("/publier/{id}")
    public ResponseEntity<Boolean> invertPublish(@PathVariable("id") Long id) {
        boolean isUpdated = mushroomService.invertPublish(id);
        if(isUpdated){
            return ResponseEntity.ok(true); // 200 en cas de succès
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found en cas d'erreur.
        }
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Long id){
        return mushroomService.delete(id);
    }

}
