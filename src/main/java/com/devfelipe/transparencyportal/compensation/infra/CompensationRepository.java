package com.devfelipe.transparencyportal.compensation.infra;

import com.devfelipe.transparencyportal.common.infra.BaseRepository;
import com.devfelipe.transparencyportal.compensation.domain.model.Compensation;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompensationRepository extends BaseRepository<Compensation, UUID> {
}
