package com.ndungutse.project_tracker.dto;

import com.ndungutse.project_tracker.model.Project;

import java.time.LocalDate;

public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDate deadline;
    private boolean status;

    // Default constructor
    public ProjectDTO() {
    }

    // Constructor with parameters
    public ProjectDTO(
            Long id,
            String name,
            String description,
            LocalDate deadline,
            boolean status
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
    }

    // Constructor without ID (for creating new projects)
    public ProjectDTO(
            String name,
            String description,
            LocalDate deadline,
            boolean status
    ) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
    }

    // Constructor from Project entity
    public ProjectDTO(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.deadline = project.getDeadline();
        this.status = project.isStatus();
    }

    // Convert Entity to DTO
    public static ProjectDTO fromEntity(Project project) {
        return new ProjectDTO(project);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Convert DTO to Entity
    public Project toEntity() {
        Project project = new Project(
                this.name,
                this.description,
                this.deadline,
                this.status
        );
        project.setId(this.id);
        return project;
    }
}
