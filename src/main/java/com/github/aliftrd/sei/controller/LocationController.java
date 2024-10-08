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

import com.github.aliftrd.sei.domain.dto.location.CreateLocationDTO;
import com.github.aliftrd.sei.domain.dto.location.UpdateLocationDTO;
import com.github.aliftrd.sei.domain.service.LocationService;
import com.github.aliftrd.sei.utils.response.ResponseFormatter;

import jakarta.validation.Valid;
import lombok.val;

@RestController
@RequestMapping("/lokasi")
public class LocationController {
    @Autowired
    private ResponseFormatter response;

    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<?> index() {
        val message = "success get data location";
        val data = locationService.findAll();

        return response.success(message, data);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateLocationDTO dto) {
        val message = "success post data location";

        return response.success(message, locationService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UpdateLocationDTO dto ) {
        val message = "success updating data location";

        return response.success(message, locationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        val message = "success delete the location";
        locationService.delete(id);
        
        return response.success(message);
    }
}