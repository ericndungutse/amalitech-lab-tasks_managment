package com.ndungutse.project_tracker.controller;

import com.ndungutse.project_tracker.dto.DeveloperDTO;
import com.ndungutse.project_tracker.service.DeveloperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/developers")
@Tag(name = "Developer", description = "Developer management APIs")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    // Create a new developer
    @Operation(summary = "Create a new developer", description = "Creates a new developer with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Developer created successfully",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeveloperDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    @PostMapping
    public ResponseEntity<DeveloperDTO> createDeveloper(
            @Parameter(description = "Developer data to create", required = true)
            @RequestBody DeveloperDTO developerDTO) {
        DeveloperDTO createdDeveloper = developerService.create(developerDTO);
        return new ResponseEntity<>(createdDeveloper, HttpStatus.CREATED);
    }

    // Get all developers
    @Operation(summary = "Get all developers", description = "Returns a list of all developers")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved developers",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeveloperDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<DeveloperDTO>> getAllDevelopers() {
        List<DeveloperDTO> developers = developerService.getAll();
        return new ResponseEntity<>(developers, HttpStatus.OK);
    }

    // Get a developer by ID
    @Operation(summary = "Get a developer by ID", description = "Returns a developer based on the provided ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved developer",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeveloperDTO.class))),
        @ApiResponse(responseCode = "404", description = "Developer not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<DeveloperDTO> getDeveloperById(
            @Parameter(description = "ID of the developer to retrieve", required = true)
            @PathVariable Long id) {
        Optional<DeveloperDTO> developer = developerService.getById(id);
        return developer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a developer
    @Operation(summary = "Update a developer", description = "Updates a developer with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Developer updated successfully",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeveloperDTO.class))),
        @ApiResponse(responseCode = "404", description = "Developer not found", content = @Content),
        @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<DeveloperDTO> updateDeveloper(
            @Parameter(description = "ID of the developer to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated developer data", required = true)
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
    @Operation(summary = "Delete a developer", description = "Deletes a developer based on the provided ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Developer deleted successfully", content = @Content),
        @ApiResponse(responseCode = "404", description = "Developer not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeveloper(
            @Parameter(description = "ID of the developer to delete", required = true)
            @PathVariable Long id) {
        if (!developerService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        developerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Assign a task to a developer
    @Operation(summary = "Assign a task to a developer", description = "Assigns a task to a developer based on their IDs")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task assigned successfully",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeveloperDTO.class))),
        @ApiResponse(responseCode = "404", description = "Developer or task not found", content = @Content)
    })
    @PostMapping("/{developerId}/tasks/{taskId}")
    public ResponseEntity<DeveloperDTO> assignTask(
            @Parameter(description = "ID of the developer", required = true)
            @PathVariable Long developerId,
            @Parameter(description = "ID of the task to assign", required = true)
            @PathVariable Long taskId
    ) {
        Optional<DeveloperDTO> developer = developerService.assignTask(developerId, taskId);
        return developer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Remove a task from a developer
    @Operation(summary = "Remove a task from a developer", description = "Removes a task from a developer based on their IDs")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task removed successfully",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeveloperDTO.class))),
        @ApiResponse(responseCode = "404", description = "Developer or task not found", content = @Content)
    })
    @DeleteMapping("/{developerId}/tasks/{taskId}")
    public ResponseEntity<DeveloperDTO> removeTask(
            @Parameter(description = "ID of the developer", required = true)
            @PathVariable Long developerId,
            @Parameter(description = "ID of the task to remove", required = true)
            @PathVariable Long taskId
    ) {
        Optional<DeveloperDTO> developer = developerService.removeTask(developerId, taskId);
        return developer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
