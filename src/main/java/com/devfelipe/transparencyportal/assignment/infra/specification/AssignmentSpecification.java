package com.devfelipe.transparencyportal.assignment.infra.specification;

import com.devfelipe.transparencyportal.assignment.domain.model.Assignment;
import com.devfelipe.transparencyportal.common.infra.specification.BaseSpecification;
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
public class AssignmentSpecification extends BaseSpecification<Assignment> {

    private String name;

    @Override
    public Predicate toPredicate(Root<Assignment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        _addLikeCondition(predicates, criteriaBuilder, root.get("name"), this.name);
        return criteriaBuilder.and(predicates.stream().toArray(Predicate[]::new));
    }
}
