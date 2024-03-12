package com.devfelipe.transparencyportal.utils;


import com.devfelipe.transparencyportal.employee.domain.enums.EmploymentType;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;

public abstract class TestConstants {
    public static final Instant BASE_INSTANT_CREATE = Instant.parse("2020-03-03T10:15:30Z");
    public static final Instant BASE_INSTANT_LAST_UPDATE = Instant.parse("2020-03-03T11:15:30Z");
    public static final String BASE_NAME = "Test";
    public static final String BASE_NAME_PRE_UPDATE = "PreUpdate";
    public static final LocalDate BASE_START_DATE = LocalDate.of(2020, 3, 3);
    public static final LocalDate BASE_START_DATE_PRE_UPDATE = LocalDate.of(2019, 3, 3);
    public static final LocalDate BASE_END_DATE = LocalDate.of(2021, 3, 3);
    public static final LocalDate BASE_END_DATE_PRE_UPDATE = LocalDate.of(2020, 3, 3);
    public static final Integer BASE_WEEKLY_WORK_HOURS = 40;
    public static final Integer BASE_WEEKLY_WORK_HOURS_PRE_UPDATE = 30;
    public static final EmploymentType BASE_EMPLOYEE_TYPE = EmploymentType.PERMANENT;
    public static final EmploymentType BASE_EMPLOYEE_TYPE_PRE_UPDATE = EmploymentType.CONTRACTED;
    public static final Integer BASE_INTEGER_ID = 1;
    public static final BigDecimal BASE_BIG_DECIMAL_VALUE = BigDecimal.TEN;
    public static final BigDecimal BASE_BIG_DECIMAL_VALUE_PRE_UPDATE = BigDecimal.ONE;
    public static final YearMonth BASE_YEAR_MONTH = YearMonth.of(2020,4);
    public static final YearMonth BASE_YEAR_MONTH_PRE_UPDATE = YearMonth.of(2019,4);

}
