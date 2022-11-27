package com.example.crypto.entity.audit;

import lombok.*;

import javax.persistence.*;
import java.time.*;

@Getter
@MappedSuperclass
@EntityListeners(AuditableEntityListener.class)
public abstract class AuditableEntity {

    @Column(name = "created_at", updatable = false, nullable = false)
    protected LocalDateTime createdAt;

    @Column(name = "created_by", updatable = false, nullable = false)
    protected String createdBy;

    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;

    @Column(name = "updated_by")
    protected String updatedBy;

    @Setter
    @Column(name = "deleted_at")
    protected LocalDateTime deletedAt;

}
