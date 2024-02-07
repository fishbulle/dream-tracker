package com.angelina.dreamtracker.controller;

import com.angelina.dreamtracker.dto.DreamResponse;
import com.angelina.dreamtracker.dto.NewDreamRequest;
import com.angelina.dreamtracker.dto.UpdateDreamRequest;
import com.angelina.dreamtracker.service.DreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dreams")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DreamController {

    private final DreamService service;

    @PostMapping("/create")
    public ResponseEntity<?> createNewDream(@RequestBody NewDreamRequest request) {
        try {
            service.newDream(request);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Something went wrong, and I don't know why...");
        }
    }

    @GetMapping("/read")
    public ResponseEntity<?> getAllDreamsByUser(@RequestParam UUID userId) {
        try {
            List<DreamResponse> dreams = service.getAllDreams(userId);
            return ResponseEntity.ok().body(dreams);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Something went wrong, and I don't know why...");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateDream(@RequestBody UpdateDreamRequest request) {
        try {
            service.updateDream(request);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Something went wrong, and I don't know why...");
        }
    }

    /* TODO add methods for DELETING dream */
}
