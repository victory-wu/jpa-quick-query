package com.victor.wu.search.interpreter.param;

public class StringParameterInterpreter implements BaseParameterInterpreter<String, String> {
    @Override
    public String apply(String value) {
        return value;
    }
}
