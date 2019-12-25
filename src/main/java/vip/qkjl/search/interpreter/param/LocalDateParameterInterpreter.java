package vip.qkjl.search.interpreter.param;

import vip.qkjl.constant.SearchConstant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
