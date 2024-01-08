package com.devfelipe.transparencyportal.compensation.domain.model;

import com.devfelipe.transparencyportal.common.converter.YearMonthConverter;
import com.devfelipe.transparencyportal.common.domain.model.BaseModel;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.YearMonth;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Compensation extends BaseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID compensationId;

    @NotNull(message = "The \"employee\" field cannot be empty")
    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    @NotNull(message = "The \"compensationReferenceYearMonth\" field cannot be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM")
    @Convert(converter = YearMonthConverter.class)
    @Column(columnDefinition = "VARCHAR(7)")
    private YearMonth compensationReferenceYearMonth;

    @NotNull(message = "The \"baseSalary\" field cannot be empty")
    @Column(precision = 10, scale = 2)
    private BigDecimal baseSalary;

    @NotNull(message = "The \"permanentAllowances\" field cannot be empty")
    @Column(precision = 10, scale = 2)
    private BigDecimal permanentAllowances;

    @NotNull(message = "The \"temporaryAllowances\" field cannot be empty")
    @Column(precision = 10, scale = 2)
    private BigDecimal temporaryAllowances;

    @NotNull(message = "The \"vacationPay\" field cannot be empty")
    @Column(precision = 10, scale = 2)
    private BigDecimal vacationPay;

    @NotNull(message = "The \"indemnityBenefits\" field cannot be empty")
    @Column(precision = 10, scale = 2)
    private BigDecimal indemnityBenefits;

    @NotNull(message = "The \"legalDeductions\" field cannot be empty")
    @Column(precision = 10, scale = 2)
    private BigDecimal legalDeductions;

    @NotNull(message = "The \"miscellaneousDeduction\" field cannot be empty")
    @Column(precision = 10, scale = 2)
    private BigDecimal miscellaneousDeduction;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalDeductions;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalGrossCompensation;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalNetCompensation;

    @NotNull(message = "The \"createdAt\" field cannot be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant updatedAt;


    @PrePersist
    @PreUpdate
    private void calculateTotalValues() {
        setTotalGrossCompensation(
                new BigDecimal(0)
                        .add(getBaseSalary())
                        .add(getPermanentAllowances())
                        .add(getTemporaryAllowances())
                        .add(getVacationPay())
                        .add(getIndemnityBenefits())
        );

        setTotalDeductions(
                new BigDecimal(0)
                        .add(getLegalDeductions())
                        .add(getMiscellaneousDeduction())
        );

        setTotalNetCompensation(
                new BigDecimal(0)
                        .add(getTotalGrossCompensation().subtract(getTotalDeductions()))
        );
    }
}
