package com.devfelipe.transparencyportal.fundingsource.infra.specification;

import com.devfelipe.transparencyportal.common.infra.specification.BaseSpecification;
import com.devfelipe.transparencyportal.fundingsource.domain.model.FundingSource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class FundingSourceSpecification extends BaseSpecification<FundingSource> {

    private String name;

    @Override
    public Predicate toPredicate(Root<FundingSource> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        _addLikeCondition(predicates, criteriaBuilder, root.get("name"), this.name );
        return criteriaBuilder.and(predicates.stream().toArray(Predicate[]::new));
    }
}
