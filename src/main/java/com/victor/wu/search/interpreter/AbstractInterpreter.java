package com.victor.wu.search.interpreter;

import com.victor.wu.constant.SearchSymbolType;
import com.victor.wu.search.interpreter.param.BaseParameterInterpreter;

public abstract class AbstractInterpreter<R> {
    /**
     * 无条件模式
     */
    protected abstract R unconditional(String[] params, String[] value) throws Exception;

    /**
     * 指定条件
     */
    protected abstract R assignConditional(String[] params, String[] value) throws Exception;
    /**
     * 指定参数类型
     */
    protected abstract R assignConditionalAndParamType(String[] params, String[] value) throws Exception;

    protected Object getValue(String[] value, SearchSymbolType symbol, Class cls) throws Exception {
        if (value == null || value.length == 0) {
            return null;
        }
        BaseParameterInterpreter parameterInterpreter = (BaseParameterInterpreter)
                Class.forName(BaseParameterInterpreter.class.getPackage().getName() + "." + cls.getSimpleName() + "ParameterInterpreter").newInstance();
        if (symbol.isArray()) {
            Object[] ret = new Object[value.length];
            for (int i = 0; i < ret.length; i++) {
                ret[i] = parameterInterpreter.apply(value[i]);
            }
            return ret;
        }
        return parameterInterpreter.apply(value[0]);
    }
}
