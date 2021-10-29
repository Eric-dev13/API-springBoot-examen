package com.api.mycologie.service;

import com.api.mycologie.entity.MushroomEntity;
import com.api.mycologie.repository.MushroomRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@RequiredArgsConstructor
@Service
public class MushroomService {

    private final MushroomRepository mushroomRepository;

    public Iterable<MushroomEntity> getAllMushrooms() {
            return mushroomRepository.findAll();
    }
}
