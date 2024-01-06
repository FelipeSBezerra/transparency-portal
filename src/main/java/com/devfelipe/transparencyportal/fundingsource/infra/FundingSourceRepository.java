package com.devfelipe.transparencyportal.fundingsource.infra;

import com.devfelipe.transparencyportal.fundingsource.domain.model.FundingSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundingSourceRepository extends JpaRepository<FundingSource, Integer> {
}
