package com.phamvanviet.demo.domain.entities;

import com.phamvanviet.demo.domain.entities.user.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class BaseEntity {
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedAt;

    @CreatedBy
    @JoinColumn(name = "createdBy")
    @OneToOne
    private User createdBy;

    @LastModifiedBy
    @JoinColumn(name = "updateBy")
    @OneToOne
    private User updatedBy;
}
