package com.ndungutse.project_tracker.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "developers")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String email;
    private String skills;

    @OneToMany(mappedBy = "developer", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    public Developer() {
    }

    public Developer(
            String name,
            String email,
            String skills
    ) {
        this.name = name;
        this.email = email;
        this.skills = skills;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        // Maintain consistency in the current session.
        tasks.add(task);
        task.setDeveloper(this);
    }

    public void removeTask(Task task) {
        // Maintain consistency in the current session.
        tasks.remove(task);
        task.setDeveloper(null);
    }
}
