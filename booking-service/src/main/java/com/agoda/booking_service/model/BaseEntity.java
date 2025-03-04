package com.agoda.booking_service.model;

import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public abstract class BaseEntity implements Serializable {

    @MongoId
    @Field("id")
    private String id = UUID.randomUUID().toString();

    @CreatedDate
    @Field("creationTimestamp")
    private LocalDateTime creationTimestamp;

    @CreatedBy
    @Field("createdBy")
    private String createdBy;

    @LastModifiedDate
    @Field("updateTimestamp")
    private LocalDateTime updateTimestamp;

    @LastModifiedBy
    @Field("lastModifiedBy")
    private String lastModifiedBy;
}