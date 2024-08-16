package com.github.aliftrd.sei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.aliftrd.sei.domain.dto.project.CreateProjectDTO;
import com.github.aliftrd.sei.domain.dto.project.UpdateProjectDTO;
import com.github.aliftrd.sei.domain.service.ProjectService;
import com.github.aliftrd.sei.utils.response.ResponseFormatter;

import jakarta.validation.Valid;
import lombok.val;

@RestController
@RequestMapping("/proyek")
public class ProjectController {
    @Autowired
    private ResponseFormatter response;

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<?> index() {
        val message = "success get data proyek";

        return response.success(message, projectService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateProjectDTO dto) {
        val message = "success post data proyek";

        return response.success(message, projectService.create(dto));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UpdateProjectDTO dto) {
        val message = "success post data proyek";

        return response.success(message, projectService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        val message = "success delete the proyek";
        projectService.delete(id);
        
        return response.success(message);
    }
}
