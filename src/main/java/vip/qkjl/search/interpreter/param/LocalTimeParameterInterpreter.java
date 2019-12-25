package vip.qkjl.search.interpreter.param;

import vip.qkjl.constant.SearchConstant;

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
