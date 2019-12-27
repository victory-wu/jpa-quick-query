package vip.qkjl.search.interpreter.param;

/**
 * 基础参数解释器
 * @author wzx
 */
public interface BaseParameterInterpreter<T, R> {
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     * @throws Exception Exception
     */
    R apply(T value) throws Exception;
}
