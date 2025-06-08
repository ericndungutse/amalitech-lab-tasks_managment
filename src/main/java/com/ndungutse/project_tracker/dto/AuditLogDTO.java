package com.ndungutse.project_tracker.dto;

import com.ndungutse.project_tracker.model.AuditLog;

import java.time.LocalDateTime;

public class AuditLogDTO {
    private String id;
    private String entityType;
    private Long entityId;
    private String action;
    private LocalDateTime timestamp;
    private String username;
    private String dataSnapshot;

    // Default constructor
    public AuditLogDTO() {
    }

    // Constructor with parameters
    public AuditLogDTO(
            String id,
            String entityType,
            Long entityId,
            String action,
            LocalDateTime timestamp,
            String username,
            String dataSnapshot
    ) {
        this.id = id;
        this.entityType = entityType;
        this.entityId = entityId;
        this.action = action;
        this.timestamp = timestamp;
        this.username = username;
        this.dataSnapshot = dataSnapshot;
    }

    // Convert Entity to DTO
    public static AuditLogDTO fromEntity(AuditLog auditLog) {
        return new AuditLogDTO(
                auditLog.getId(),
                auditLog.getEntityType(),
                auditLog.getEntityId(),
                auditLog.getAction(),
                auditLog.getTimestamp(),
                auditLog.getUsername(),
                auditLog.getDataSnapshot()
        );
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