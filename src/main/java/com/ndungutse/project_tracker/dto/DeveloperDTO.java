package com.ndungutse.project_tracker.dto;

import com.ndungutse.project_tracker.model.Developer;
import com.ndungutse.project_tracker.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeveloperDTO {
    private Long id;
    private String name;
    private String email;
    private String skills;
    private List<Long> taskIds;

    // Default constructor
    public DeveloperDTO() {
    }

    // Constructor with all parameters
    public DeveloperDTO(
            Long id,
            String name,
            String email,
            String skills,
            List<Long> taskIds) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.skills = skills;
        this.taskIds = taskIds;
    }

    // Constructor without ID (for creating new developers)
    public DeveloperDTO(
            String name,
            String email,
            String skills) {
        this.name = name;
        this.email = email;
        this.skills = skills;
        this.taskIds = new ArrayList<>();
    }

    // Constructor from Developer entity
    public DeveloperDTO(Developer developer) {
        this.id = developer.getId();
        this.name = developer.getName();
        this.email = developer.getEmail();
        this.skills = developer.getSkills();
        this.taskIds = developer.getTasks().stream()
                .map(Task::getId)
                .collect(Collectors.toList());
    }

    // Convert Entity to DTO
    public static DeveloperDTO fromEntity(Developer developer) {
        return new DeveloperDTO(developer);
    }

    // Convert DTO to Entity
    public Developer toEntity() {
        Developer developer = new Developer(
                this.name,
                this.email,
                this.skills
        );
        developer.setId(this.id);
        return developer;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public List<Long> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(List<Long> taskIds) {
        this.taskIds = taskIds;
    }
}