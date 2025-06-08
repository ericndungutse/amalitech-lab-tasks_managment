package com.ndungutse.project_tracker.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "projects")
@DynamicUpdate
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;
    private LocalDate deadline;
    private boolean status;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    // Default constructor
    public Project() {
    }

    // Constructor with parameters
    public Project(
            String name,
            String description,
            LocalDate deadline,
            boolean status) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        task.setProject(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.setProject(null);
    }
}
