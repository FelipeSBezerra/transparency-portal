package com.devfelipe.transparencyportal.compensation.infra.specification;

import com.devfelipe.transparencyportal.common.infra.specification.BaseSpecification;
import com.devfelipe.transparencyportal.compensation.domain.model.Compensation;
import jakarta.persistence.criteria.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class CompensationSpecification extends BaseSpecification<Compensation> {

    private Integer employeeId;
    private String employeeName;
    private YearMonth compensationReferenceYearMonth;
    private BigDecimal minBaseSalary;
    private BigDecimal maxBaseSalary;
    private BigDecimal minPermanentAllowances;
    private BigDecimal maxPermanentAllowances;
    private BigDecimal minTemporaryAllowances;
    private BigDecimal maxTemporaryAllowances;
    private BigDecimal minVacationPay;
    private BigDecimal maxVacationPay;
    private BigDecimal minIndemnityBenefits;
    private BigDecimal maxIndemnityBenefits;
    private BigDecimal minLegalDeductions;
    private BigDecimal maxLegalDeductions;
    private BigDecimal minMiscellaneousDeduction;
    private BigDecimal maxMiscellaneousDeduction;
    private BigDecimal minTotalDeductions;
    private BigDecimal maxTotalDeductions;
    private BigDecimal minTotalGrossCompensation;
    private BigDecimal maxTotalGrossCompensation;
    private BigDecimal minTotalNetCompensation;
    private BigDecimal maxTotalNetCompensation;


    @Override
    public Predicate toPredicate(Root<Compensation> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        _addEqualCondition(predicates, criteriaBuilder, root.join("employee").get("employeeId"), this.employeeId);
        _addLikeCondition(predicates, criteriaBuilder, root.join("employee").get("name"), this.employeeName);
        _addEqualCondition(predicates, criteriaBuilder, root.get("compensationReferenceYearMonth"), this.compensationReferenceYearMonth);
        _addRangeCondition(predicates, criteriaBuilder, root.get("baseSalary"), this.minBaseSalary, this.maxBaseSalary);
        _addRangeCondition(predicates, criteriaBuilder, root.get("permanentAllowances"), this.minPermanentAllowances, this.maxPermanentAllowances);
        _addRangeCondition(predicates, criteriaBuilder, root.get("temporaryAllowances"), this.minTemporaryAllowances, this.maxTemporaryAllowances);
        _addRangeCondition(predicates, criteriaBuilder, root.get("vacationPay"), this.minVacationPay, this.maxVacationPay);
        _addRangeCondition(predicates, criteriaBuilder, root.get("indemnityBenefits"), this.minIndemnityBenefits, this.maxIndemnityBenefits);
        _addRangeCondition(predicates, criteriaBuilder, root.get("legalDeductions"), this.minLegalDeductions, this.maxLegalDeductions);
        _addRangeCondition(predicates, criteriaBuilder, root.get("miscellaneousDeduction"), this.minMiscellaneousDeduction, this.maxMiscellaneousDeduction);
        _addRangeCondition(predicates, criteriaBuilder, root.get("totalDeductions"), this.minTotalDeductions, this.maxTotalDeductions);
        _addRangeCondition(predicates, criteriaBuilder, root.get("totalGrossCompensation"), this.minTotalGrossCompensation, this.maxTotalGrossCompensation);
        _addRangeCondition(predicates, criteriaBuilder, root.get("totalNetCompensation"), this.minTotalNetCompensation, this.maxTotalNetCompensation);

        return criteriaBuilder.and(predicates.stream().toArray(Predicate[]::new));
    }

    private void _addRangeCondition(List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Expression<BigDecimal> expression, BigDecimal min, BigDecimal max) {
        _addGreaterThanOrEqualCondition(predicates, criteriaBuilder, expression, min);
        _addLessThanOrEqualCondition(predicates, criteriaBuilder, expression, max);
    }
}
