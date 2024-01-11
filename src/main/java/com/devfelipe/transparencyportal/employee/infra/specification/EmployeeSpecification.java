package com.devfelipe.transparencyportal.employee.infra.specification;

import com.devfelipe.transparencyportal.common.infra.specification.BaseSpecification;
import com.devfelipe.transparencyportal.employee.domain.enums.EmploymentType;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
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
public class EmployeeSpecification extends BaseSpecification<Employee> {

    private String name;
    private EmploymentType employmentType;
    private Integer jobTitleId;
    private String jobTitleName;
    private Integer fundingSourceId;
    private String fundingSourceName;
    private Integer assignmentId;
    private String assignmentName;


    @Override
    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        _addLikeCondition(predicates, criteriaBuilder, root.get("name"), this.name);
        _addEqualCondition(predicates, criteriaBuilder, root.get("employmentType"), this.employmentType);
        _addEqualCondition(predicates, criteriaBuilder, root.join("jobTitle").get("jobTitleId"), this.jobTitleId);
        _addLikeCondition(predicates, criteriaBuilder, root.join("jobTitle").get("name"), this.jobTitleName);
        _addEqualCondition(predicates, criteriaBuilder, root.join("fundingSource").get("fundingSourceId"), this.fundingSourceId);
        _addLikeCondition(predicates, criteriaBuilder, root.join("fundingSource").get("name"), this.fundingSourceName);
        _addEqualCondition(predicates, criteriaBuilder, root.join("assignment").get("assignmentId"), this.assignmentId);
        _addLikeCondition(predicates, criteriaBuilder, root.join("assignment").get("name"), this.assignmentName);

        return criteriaBuilder.and(predicates.stream().toArray(Predicate[]::new));
    }
}
