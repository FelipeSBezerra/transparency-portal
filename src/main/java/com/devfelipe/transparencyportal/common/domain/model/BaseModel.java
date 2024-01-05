package com.devfelipe.transparencyportal.common.domain.model;

import jakarta.persistence.MappedSuperclass;

import java.time.Instant;

@MappedSuperclass
public abstract class BaseModel {

    public abstract void setCreatedAt(Instant instant);
    public abstract void setUpdatedAt(Instant instant);
}
