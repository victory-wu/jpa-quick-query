package com.victor.wu.search.interpreter.param;

import com.victor.wu.constant.SearchConstant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeParameterInterpreter implements BaseParameterInterpreter<String, LocalDateTime> {

    @Override
    public LocalDateTime apply(String value) {
        for (String pattern : SearchConstant.datePatterns) {
            if (value.length() == pattern.length()){
                return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern));
            }
        }
        return LocalDateTime.parse(value);
    }
}
