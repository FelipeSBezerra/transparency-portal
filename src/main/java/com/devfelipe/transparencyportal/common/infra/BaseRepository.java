package com.devfelipe.transparencyportal.common.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<EntityClass, EntityIdType> extends JpaRepository<EntityClass, EntityIdType>, JpaSpecificationExecutor<EntityClass> {
}
