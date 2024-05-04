package com.api.mushroom.controller.admin.edibility;

import com.api.mushroom.Mapper.MapStructMapper;
import com.api.mushroom.controller.dto.EdibilityDto;
import com.api.mushroom.entity.EdibilityEntity;
import com.api.mushroom.service.EdibilityService;
import com.api.mushroom.service.model.EdibilityServiceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.management.relation.RoleNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/admin/edibility")
public class EdibiltyCrudController {

    private final EdibilityService edibilityService;
    private final MapStructMapper mapStructMapper;


    @GetMapping
    public List<EdibilityDto> getAll() {
        List<EdibilityServiceModel> edibilityServiceModels = edibilityService.getAll();
        return edibilityServiceModels.stream().map(mapStructMapper::edibilityServiceEdibilityDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EdibilityDto> getById(@PathVariable("id") Long id) {
        try {
            EdibilityServiceModel edibilityServiceModel = edibilityService.getById(id);
            return ResponseEntity.ok(mapStructMapper.edibilityServiceEdibilityDto(edibilityServiceModel));
        } catch (RoleNotFoundException e) {
            // Gérer l'erreur lorsque le rôle n'est pas trouvé
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public boolean addEdibilityWithFile(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws IOException {
       if (file != null) {
           return edibilityService.addEdibilityWithFile(file, name);
       }
       return false;
    }

    @PutMapping("/{id}")
    public boolean put(@PathVariable("id") Long id, @RequestBody EdibilityDto edibilityDto) {
        EdibilityServiceModel edibilityServiceModel = mapStructMapper.edibilityDtoToEdibilityService(edibilityDto);
        return edibilityService.put(edibilityServiceModel);
    }

    @DeleteMapping("/{id}")
    public boolean deleter(@PathVariable("id") Long id){
         // TODO : gerer la suppression des fichiers
        return edibilityService.delete(id);
    }

}
