package com.ndungutse.project_tracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "developers")
public class Developers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String email;
    private String skills;

    public Developers() {
    }

    public Developers(
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
}
