package com.api.mushroom.controller.admin.lamellaType;

import com.api.mushroom.Mapper.MapStructMapper;
import com.api.mushroom.controller.dto.LamellatypeDto;
import com.api.mushroom.service.LamellaTypeService;
import com.api.mushroom.service.model.LamellatypeServiceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin/lamellaType")
public class LamellaTypeCrudController {

    private final LamellaTypeService lamellaTypeService;
    private final MapStructMapper mapStructMapper;


    @GetMapping
    public List<LamellatypeDto> getAll() {
        List<LamellatypeServiceModel> lamellatypeServiceModels = lamellaTypeService.getAll();
        return lamellatypeServiceModels.stream().map(mapStructMapper::lamellatypeServiceLamellatypeDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LamellatypeDto> getById(@PathVariable("id") Long id) {
        try {
            LamellatypeServiceModel lamellatypeServiceModel = lamellaTypeService.getById(id);
            return ResponseEntity.ok(mapStructMapper.lamellatypeServiceLamellatypeDto(lamellatypeServiceModel));
        }  catch (RoleNotFoundException e) {
            // Gérer l'erreur lorsque le rôle n'est pas trouvé
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public boolean add(@RequestBody LamellatypeDto lamellatypeDto) {
        LamellatypeServiceModel lamellatypeServiceModel = mapStructMapper.lamellatypeDtoToLamellatypeService(lamellatypeDto);
        return lamellaTypeService.add(lamellatypeServiceModel);
    }

    @PutMapping("/{id}")
    public boolean put(@PathVariable("id") Long id, @RequestBody LamellatypeDto lamellatypeDto) {
        LamellatypeServiceModel lamellatypeServiceModel = mapStructMapper.lamellatypeDtoToLamellatypeService(lamellatypeDto);
        return lamellaTypeService.put(lamellatypeServiceModel);
    }

    @DeleteMapping("/{id}")
    public boolean deleter(@PathVariable("id") Long id){
        return lamellaTypeService.delete(id);
    }
}
