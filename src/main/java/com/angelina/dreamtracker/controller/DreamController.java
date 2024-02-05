package com.angelina.dreamtracker.controller;

import com.angelina.dreamtracker.dto.NewDreamRequest;
import com.angelina.dreamtracker.service.DreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dreams")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DreamController {

    private final DreamService service;

    @PostMapping
    public ResponseEntity<?> createNewDream(@RequestBody NewDreamRequest request) {
        try {
            service.newDream(request);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Something went wrong, and I don't know why...");
        }
        return null;
    }
}
