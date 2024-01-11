package com.devfelipe.transparencyportal.fundingsource.infra;

import com.devfelipe.transparencyportal.common.infra.BaseRepository;
import com.devfelipe.transparencyportal.fundingsource.domain.model.FundingSource;
import org.springframework.stereotype.Repository;

@Repository
public interface FundingSourceRepository extends BaseRepository<FundingSource, Integer> {
}
