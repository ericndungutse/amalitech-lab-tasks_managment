package com.ndungutse.project_tracker.controller;

import com.ndungutse.project_tracker.dto.DeveloperDTO;
import com.ndungutse.project_tracker.service.DeveloperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    // Create a new developer
    @PostMapping
    public ResponseEntity<DeveloperDTO> createDeveloper(@RequestBody DeveloperDTO developerDTO) {
        DeveloperDTO createdDeveloper = developerService.create(developerDTO);
        return new ResponseEntity<>(createdDeveloper, HttpStatus.CREATED);
    }

    // Get all developers
    @GetMapping
    public ResponseEntity<List<DeveloperDTO>> getAllDevelopers() {
        List<DeveloperDTO> developers = developerService.getAll();
        return new ResponseEntity<>(developers, HttpStatus.OK);
    }

    // Get a developer by ID
    @GetMapping("/{id}")
    public ResponseEntity<DeveloperDTO> getDeveloperById(@PathVariable Long id) {
        Optional<DeveloperDTO> developer = developerService.getById(id);
        return developer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a developer
    @PatchMapping("/{id}")
    public ResponseEntity<DeveloperDTO> updateDeveloper(
            @PathVariable Long id,
            @RequestBody DeveloperDTO developerDTO
    ) {
        if (!developerService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<DeveloperDTO> updatedDeveloper = developerService.update(id, developerDTO);
        return updatedDeveloper.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    // Delete a developer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeveloper(@PathVariable Long id) {
        if (!developerService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        developerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Assign a task to a developer
    @PostMapping("/{developerId}/tasks/{taskId}")
    public ResponseEntity<DeveloperDTO> assignTask(
            @PathVariable Long developerId,
            @PathVariable Long taskId
    ) {
        Optional<DeveloperDTO> developer = developerService.assignTask(developerId, taskId);
        return developer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Remove a task from a developer
    @DeleteMapping("/{developerId}/tasks/{taskId}")
    public ResponseEntity<DeveloperDTO> removeTask(
            @PathVariable Long developerId,
            @PathVariable Long taskId
    ) {
        Optional<DeveloperDTO> developer = developerService.removeTask(developerId, taskId);
        return developer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}