package com.ndungutse.project_tracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "audit_logs")
public class AuditLog {
    @Id
    private String id;
    private String entityType;
    private Long entityId;
    private String action;
    private LocalDateTime timestamp;
    private String username;
    private String dataSnapshot;

    // Default constructor
    public AuditLog() {
    }

    // Constructor with parameters
    public AuditLog(String entityType, Long entityId, String action, String username, String dataSnapshot) {
        this.entityType = entityType;
        this.entityId = entityId;
        this.action = action;
        this.timestamp = LocalDateTime.now();
        this.username = username;
        this.dataSnapshot = dataSnapshot;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDataSnapshot() {
        return dataSnapshot;
    }

    public void setDataSnapshot(String dataSnapshot) {
        this.dataSnapshot = dataSnapshot;
    }
}