package com.github.aliftrd.sei.domain.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.github.aliftrd.sei.domain.dto.project.CreateProjectDTO;
import com.github.aliftrd.sei.domain.dto.project.FindProjectDTO;
import com.github.aliftrd.sei.domain.dto.project.UpdateProjectDTO;
import com.github.aliftrd.sei.domain.entity.Location;
import com.github.aliftrd.sei.domain.entity.Project;
import com.github.aliftrd.sei.domain.repository.LocationRepository;
import com.github.aliftrd.sei.domain.repository.ProjectRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Iterable<FindProjectDTO> findAll() {
        Iterable<Project> projects = projectRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        List<FindProjectDTO> dtos = new ArrayList<>();

        for(Project project : projects) {
            dtos.add(modelMapper.map(project, FindProjectDTO.class));
        }

        return dtos;
    }

    public FindProjectDTO create(CreateProjectDTO dto) {
        Project project = modelMapper.map(dto, Project.class);
        Set<Location> locations = new HashSet<>();
        
        if(!dto.getLocationIds().isEmpty()) {
            for (Long locationId : dto.getLocationIds()) {
                Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
                locations.add(location);
            }
        }
        
        project.setLocations(locations);

        return modelMapper.map(projectRepository.save(project), FindProjectDTO.class);
    }

    public FindProjectDTO update(Long id, UpdateProjectDTO dto) {
        Project oldProject = projectRepository.findById(id).get();
        modelMapper.map(dto, oldProject);
        
        Set<Location> locations = new HashSet<>();

        if(!dto.getLocationIds().isEmpty()) {
            for (Long locationId : dto.getLocationIds()) {
                Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
                locations.add(location);
            }
        }
        
        oldProject.setLocations(locations);

        return modelMapper.map(projectRepository.save(oldProject), FindProjectDTO.class);
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
