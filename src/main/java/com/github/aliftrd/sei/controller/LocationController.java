package com.github.aliftrd.sei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.aliftrd.sei.utils.response.ResponseFormatter;

import lombok.val;

@RestController
@RequestMapping("/lokasi")
public class LocationController {
    @Autowired
    ResponseFormatter response;

    @GetMapping
    public ResponseEntity<?> index() {
        val message = "success get data location";
        // val data = new Object()

        return response.success(message);
    }
}
