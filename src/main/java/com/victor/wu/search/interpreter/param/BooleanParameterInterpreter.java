package com.victor.wu.search.interpreter.param;

public class BooleanParameterInterpreter implements BaseParameterInterpreter<String, Boolean> {

    @Override
    public Boolean apply(String value) {
        if (value.length() == 1) {
            return value.equals("1");
        }
        return Boolean.parseBoolean(value);
    }

}
