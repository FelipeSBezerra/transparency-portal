package com.devfelipe.transparencyportal.jobtitle.infra.specification;

import com.devfelipe.transparencyportal.common.infra.specification.BaseSpecification;
import com.devfelipe.transparencyportal.jobtitle.domain.model.JobTitle;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class JobTitleSpecification extends BaseSpecification<JobTitle> {

    private String name;

    @Override
    public Predicate toPredicate(Root<JobTitle> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        _addLikeCondition(predicates, criteriaBuilder, root.get("name"), this.name);
        return criteriaBuilder.and(predicates.stream().toArray(Predicate[]::new));
    }
}
