package com.devfelipe.transparencyportal.employee.domain.model;

import com.devfelipe.transparencyportal.common.domain.model.BaseModel;
import com.devfelipe.transparencyportal.employee.domain.enums.EmploymentType;
import com.devfelipe.transparencyportal.jobtitle.domain.model.JobTitle;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Employee extends BaseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Integer employeeId;

    @NotBlank(message = "The \"name\" field cannot be empty")
    private String name;

    @NotNull(message = "The \"employmentType\" field cannot be empty")
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    @NotNull(message = "The \"employmentStartDate\" field cannot be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate employmentStartDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate employmentEndDate;

    @NotNull(message = "The \"weeklyWorkHours\" field cannot be empty")
    private Integer weeklyWorkHours;

    @NotNull(message = "The \"jobTitle\" field cannot be empty")
    @ManyToOne
    @JoinColumn(name = "jobTitleId")
    private JobTitle jobTitle;

    @NotNull(message = "The \"createdAt\" field cannot be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant updatedAt;
}
