package com.ndungutse.project_tracker.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String description;
    private boolean status;
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @Column(name = "developer_id", insertable = false, updatable = false)
    private Long developerId;

    public Task() {
    }

    public Task(
            String title,
            String description,
            boolean status,
            LocalDate dueDate,
            Project project
    ) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.project = project;
    }

    // Constructor with all parameters including optional developer
    public Task(
            String title,
            String description,
            boolean status,
            LocalDate dueDate,
            Project project,
            Developer developer
    ) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.project = project;
        this.developer = developer;
    }

    public Long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Long developerId) {
        this.developerId = developerId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }


}
