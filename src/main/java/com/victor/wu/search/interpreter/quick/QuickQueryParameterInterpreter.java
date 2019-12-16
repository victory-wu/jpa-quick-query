package com.victor.wu.search.interpreter.quick;


import com.victor.wu.constant.SearchConstant;
import com.victor.wu.constant.SearchParameterType;
import com.victor.wu.constant.SearchSymbolType;
import com.victor.wu.exception.IncorrectFormatException;
import com.victor.wu.query.Condition;
import com.victor.wu.search.interpreter.AbstractInterpreter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class QuickQueryParameterInterpreter extends AbstractInterpreter<Condition> {

    public Condition invoke(String param, String[] value) throws Exception {
        param = param.substring(2); // 过滤边角料
        String[] params = param.split(SearchConstant.DYNAMIC_SEARCH_PARAMETER_SEPARATOR);
        switch (params.length) {
            case 1:
                return unconditional(params, value);
            case 2:
                return assignConditional(params, value);
            case 3:
                return assignConditionalAndParamType(params, value);
            default:
                throw new IncorrectFormatException("格式错误！");
        }
    }

    /**
     * 无条件模式
     */
    @Override
    protected Condition unconditional(String[] params, String[] value) throws Exception {
        String paramName = params[0];
        return Condition.eq(paramName, getValue(value, SearchSymbolType.EQ, String.class), true);
    }

    /**
     * 指定条件
     */
    @Override
    protected Condition assignConditional(String[] params, String[] value) throws Exception {
        String paramName = params[0];
        SearchSymbolType symbol = SearchSymbolType.valueOf(params[1]);
        String paramCondition = symbol.name().toLowerCase();
        Method method = getMethod(Condition.class, paramCondition);
        return (Condition) method.invoke(null, paramName, getValue(value, symbol, String.class), true);
    }
    /**
     * 指定参数类型
     */
    @Override
    protected Condition assignConditionalAndParamType(String[] params, String[] value) throws Exception {
        String paramName = params[0];
        SearchSymbolType symbol = SearchSymbolType.valueOf(params[1]);
        String paramCondition = symbol.name().toLowerCase();
        String paramType = params[2];
        Method method = getMethod(Condition.class, paramCondition);
        return (Condition) method.invoke(null, paramName, getValue(value, symbol, SearchParameterType.valueOf(paramType).getCls()), true);
    }

    Method getMethod(Class cls, String methodName) throws NoSuchMethodException {
        for (Method item : Condition.class.getDeclaredMethods()) {
            if (item.getName().equals(methodName)) {
                return item;
            }
        }
        throw new NoSuchMethodException();
    }
}
