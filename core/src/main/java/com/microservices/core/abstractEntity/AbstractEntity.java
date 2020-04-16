package com.microservices.core.abstractEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime customerAt;

    @CreationTimestamp
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    @Column(columnDefinition = "datetime DEFAULT NOW()")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    @Column(columnDefinition = "datetime DEFAULT NOW()")
    private LocalDateTime updatedAt;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime deletedAt;

    @PreUpdate
    @PrePersist
    private void updateDate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        } else {
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }
}
