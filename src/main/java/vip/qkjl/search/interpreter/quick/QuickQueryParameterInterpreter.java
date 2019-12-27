package vip.qkjl.search.interpreter.quick;


import vip.qkjl.constant.SearchConstant;
import vip.qkjl.constant.SearchParameterType;
import vip.qkjl.constant.SearchSymbolType;
import vip.qkjl.exception.IncorrectFormatException;
import vip.qkjl.query.Condition;
import vip.qkjl.search.interpreter.AbstractInterpreter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 快捷查询解释器
 * @author wzx
 */
@Component
public class QuickQueryParameterInterpreter extends AbstractInterpreter<Condition> {


    /**
     * 主动调用
     * @param param 参数名
     * @param value 值
     * @return Condition
     * @throws Exception Exception
     */
    public Condition invoke(String param, String[] value) throws Exception {
        /**
         * 过滤前缀内容
         */
        param = param.substring(2);
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
     * 无条件查询
     * @param params 参数
     * @param value 查询值
     * @return Condition
     * @throws Exception Exception
     */
    @Override
    protected Condition unconditional(String[] params, String[] value) throws Exception {
        String paramName = params[0];
        return Condition.eq(paramName, getValue(value, SearchSymbolType.EQ, String.class), true);
    }

    /**
     * 按条件查询
     * @param params 参数
     * @param value 查询值
     * @return Condition
     * @throws Exception Exception
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
     * 指定参数查询
     * @param params 参数
     * @param value 查询值
     * @return Condition
     * @throws Exception Exception
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

    /**
     * 获取方法
     * @param cls 类
     * @param methodName 方法名字
     * @return
     * @throws NoSuchMethodException
     */
    Method getMethod(Class cls, String methodName) throws NoSuchMethodException {
        for (Method item : cls.getDeclaredMethods()) {
            if (item.getName().equals(methodName)) {
                return item;
            }
        }
        throw new NoSuchMethodException();
    }
}
