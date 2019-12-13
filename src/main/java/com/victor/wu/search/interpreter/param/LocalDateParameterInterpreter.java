package com.victor.wu.search.interpreter.param;

import com.victor.wu.constant.SearchConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateParameterInterpreter implements BaseParameterInterpreter<String, LocalDate> {

    @Override
    public LocalDate apply(String value) {
        for (String pattern : SearchConstant.datePatterns) {
            if (value.length() == pattern.length()){
                return LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern));
            }
        }
        return LocalDate.parse(value);
    }
}
