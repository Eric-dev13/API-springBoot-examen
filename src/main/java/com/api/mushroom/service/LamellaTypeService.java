package com.api.mushroom.service;

import com.api.mushroom.Mapper.MapStructMapper;
import com.api.mushroom.entity.EdibilityEntity;
import com.api.mushroom.entity.LamellatypeEntity;
import com.api.mushroom.repository.LamellaTypeJpaRepository;
import com.api.mushroom.service.model.LamellatypeServiceModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LamellaTypeService {

    private final LamellaTypeJpaRepository lamellaTypeJpaRepository;
    private final MapStructMapper mapStructMapper;


    public List<LamellatypeServiceModel> getAll() {
        List<LamellatypeEntity> lamellatype = lamellaTypeJpaRepository.findAll();
        return lamellatype.stream().map(mapStructMapper::lamellatypeEntityToLamellatypeService).collect(Collectors.toList());
    }

    public LamellatypeServiceModel getById(Long id) throws RoleNotFoundException {
        LamellatypeEntity lamellatype = lamellaTypeJpaRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("La référence avec l'ID spécifié n'a pas été trouvé."));
        return mapStructMapper.lamellatypeEntityToLamellatypeService(lamellatype);
    }

    public boolean add(LamellatypeServiceModel lamellatypeServiceModel) {
        LamellatypeEntity lamellatypeEntity = mapStructMapper.lamellatypeServiceToLamellatypeEntity(lamellatypeServiceModel);
        LamellatypeEntity savedLamellatypeEntity = lamellaTypeJpaRepository.save(lamellatypeEntity);
        return savedLamellatypeEntity != null;
    }

    public boolean put(LamellatypeServiceModel lamellatypeServiceModel){
        LamellatypeEntity lamellatypeEntity = mapStructMapper.lamellatypeServiceToLamellatypeEntity(lamellatypeServiceModel);
        LamellatypeEntity savedLamellatypeEntity = lamellaTypeJpaRepository.save(lamellatypeEntity);
        return savedLamellatypeEntity != null;
    }

    public boolean delete(Long id) {
        if(lamellaTypeJpaRepository.existsById(id)) {
            lamellaTypeJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
