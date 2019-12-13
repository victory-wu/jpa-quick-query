package com.victor.wu.search.interpreter.param;

import java.text.ParseException;

public interface BaseParameterInterpreter<T, R> {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T value) throws Exception;
}
