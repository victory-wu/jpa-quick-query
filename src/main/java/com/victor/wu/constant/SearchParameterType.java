package com.victor.wu.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@Getter
public enum SearchParameterType {
    N(Number.class),
    L(Long.class),
    B(Boolean.class),
    S(String.class),
    D(Date.class),
    LDT(LocalDateTime.class),
    LD(LocalDate.class),
    LT(LocalTime.class);
    private Class cls;
}
