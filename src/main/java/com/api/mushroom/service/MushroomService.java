package com.api.mushroom.service;

import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.repository.MushroomRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@RequiredArgsConstructor
@Service
public class MushroomService {

    private final MushroomRepository mushroomRepository;

    public Iterable<MushroomEntity> getAllMushrooms() {
            return mushroomRepository.findAll();
    }
}
