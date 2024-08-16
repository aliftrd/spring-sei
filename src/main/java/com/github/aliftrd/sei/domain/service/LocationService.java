package com.github.aliftrd.sei.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.aliftrd.sei.domain.dto.location.CreateLocationDTO;
import com.github.aliftrd.sei.domain.dto.location.FindLocationDTO;
import com.github.aliftrd.sei.domain.dto.location.UpdateLocationDTO;
import com.github.aliftrd.sei.domain.entity.Location;
import com.github.aliftrd.sei.domain.repository.LocationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LocationService  {
    @Autowired
    private LocationRepository locationRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Iterable<FindLocationDTO> findAll() {
        Iterable<Location> locations = locationRepo.findAll();

        List<FindLocationDTO> dtos = new ArrayList<>();

        for (Location location : locations) {
            dtos.add(modelMapper.map(location, FindLocationDTO.class));
        }

        return dtos;
    }

    public FindLocationDTO create(CreateLocationDTO dto) {
        Location location = locationRepo.save(modelMapper.map(dto, Location.class));

        return modelMapper.map(location, FindLocationDTO.class);
    }

    public FindLocationDTO update(Long id, UpdateLocationDTO dto) {
        Location oldLocation = locationRepo.findById(id).get();
        modelMapper.map(dto, oldLocation);

        return modelMapper.map(locationRepo.save(oldLocation), FindLocationDTO.class);
    }

    public void delete(Long id) {
        locationRepo.deleteById(id);
    }
}
