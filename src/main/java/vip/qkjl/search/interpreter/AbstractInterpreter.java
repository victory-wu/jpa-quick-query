package vip.qkjl.search.interpreter;

import vip.qkjl.constant.SearchSymbolType;
import vip.qkjl.search.interpreter.param.BaseParameterInterpreter;

/**
 * AbstractInterpreter
 * @author wzx
 */
public abstract class AbstractInterpreter<R> {
    /**
     * 无条件查询
     * @param params 参数
     * @param value 查询值
     * @return Condition
     * @throws Exception Exception
     */
    protected abstract R unconditional(String[] params, String[] value) throws Exception;

    /**
     * 按条件查询
     * @param params 参数
     * @param value 查询值
     * @return Condition
     * @throws Exception Exception
     */
    protected abstract R assignConditional(String[] params, String[] value) throws Exception;

    /**
     * 指定参数查询
     * @param params 参数
     * @param value 查询值
     * @return Condition
     * @throws Exception Exception
     */
    protected abstract R assignConditionalAndParamType(String[] params, String[] value) throws Exception;

    /**
     * 解析值
     * @param value 值
     * @param symbol 表达式
     * @param cls 类型
     * @return 值
     * @throws Exception Exception
     */
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
