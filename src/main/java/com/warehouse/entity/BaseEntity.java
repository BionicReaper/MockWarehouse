package com.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Abstract base class for entities, providing common fields such as ID,
 * creation timestamp, and update timestamp.
 * <p>
 * All entities that extend this class will inherit these fields and
 * automatic timestamp management.
 */
@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public abstract class BaseEntity {

    /**
     * Unique identifier for the entity.
     * This value is auto-generated using the identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Timestamp indicating when the entity was created.
     * This field is automatically set before the entity is persisted.
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp indicating when the entity was last updated.
     * This field is automatically updated before the entity is updated.
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Lifecycle callback method triggered before the entity is persisted.
     * Sets both {@code createdAt} and {@code updatedAt} to the current time.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Lifecycle callback method triggered before the entity is updated.
     * Updates the {@code updatedAt} field to the current time.
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
