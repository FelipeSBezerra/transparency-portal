package com.devfelipe.transparencyportal.common.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base repository interface providing common CRUD operations and specification execution for entities.
 *
 * @param <T>  The type of the entity.
 * @param <ID> The type of the entity's unique identifier.
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
