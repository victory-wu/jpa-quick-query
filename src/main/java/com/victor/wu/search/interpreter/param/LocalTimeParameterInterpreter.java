package com.victor.wu.search.interpreter.param;

import com.victor.wu.constant.SearchConstant;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeParameterInterpreter implements BaseParameterInterpreter<String, LocalTime> {

    @Override
    public LocalTime apply(String value) {
        for (String pattern : SearchConstant.datePatterns) {
            if (value.length() == pattern.length()){
                return LocalTime.parse(value, DateTimeFormatter.ofPattern(pattern));
            }
        }
        return LocalTime.parse(value);
    }
}
