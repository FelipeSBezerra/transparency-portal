package com.devfelipe.transparencyportal.compensation.infra;

import com.devfelipe.transparencyportal.compensation.domain.model.Compensation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompensationRepository extends JpaRepository<Compensation, UUID> {
}
