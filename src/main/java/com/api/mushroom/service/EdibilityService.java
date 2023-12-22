package com.api.mushroom.service;

import com.api.mushroom.Mapper.MapStructMapper;
import com.api.mushroom.entity.EdibilityEntity;
import com.api.mushroom.repository.EdibilityJpaRepository;
import com.api.mushroom.service.model.EdibilityServiceModel;
import com.api.mushroom.service.utils.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.management.relation.RoleNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class EdibilityService {

    private final EdibilityJpaRepository edibilityJpaRepository;
    private final FileUploadService fileUploadService;
    private final MapStructMapper mapStructMapper;


    public List<EdibilityServiceModel> getAll() {
        List<EdibilityEntity> edibilityEntities = edibilityJpaRepository.findAll();
        return edibilityEntities.stream().map(mapStructMapper::edibilityEntityToEdibilityService).collect(Collectors.toList());
    }

    public EdibilityServiceModel getById(Long id) throws RoleNotFoundException {
        EdibilityEntity edibilityEntity = edibilityJpaRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("La fiche avec l'ID spécifié n'a pas été trouvé."));
        return mapStructMapper.edibilityEntityToEdibilityService(edibilityEntity);
    }

    public boolean addEdibilityWithFile(MultipartFile file, String name) throws IOException {
        // upload du fichier
        String newFilename = fileUploadService.fileUpload(file, "edibility/");
        EdibilityEntity edibilityEntity = new EdibilityEntity();
        edibilityEntity.setName(name);
        edibilityEntity.setFilename(newFilename);
        EdibilityEntity edibility = edibilityJpaRepository.save(edibilityEntity);
        return edibility != null ;
    }

    public boolean put(EdibilityServiceModel edibilityServiceModel){
        EdibilityEntity edibilityEntity = mapStructMapper.edibilityServiceToEdibilityEntity(edibilityServiceModel);
        EdibilityEntity savedEdibilityEntity = edibilityJpaRepository.save(edibilityEntity);
        return savedEdibilityEntity != null;
    }

    public boolean delete(Long id) {
        if(edibilityJpaRepository.existsById(id)){
            edibilityJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
