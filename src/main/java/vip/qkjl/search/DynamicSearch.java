package vip.qkjl.search;

import vip.qkjl.constant.SearchConstant;
import vip.qkjl.query.Q;
import vip.qkjl.search.interpreter.quick.QuickQueryParameterInterpreter;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 动态搜索：支持规则
 * 参数用 下划线分割 _ 例如：S_属性名_LK_A   S_username_EQ_S
 *
 *
 *
 *
 *
 * @author wzx
 */
public class DynamicSearch extends LinkedHashMap<String, String[]> {

    private QuickQueryParameterInterpreter sqlQueryParameterInterpreter = new QuickQueryParameterInterpreter();

    public Q getQ(HttpServletRequest request) {
        Q q = new Q();
        try {
            for (Map.Entry<String, String[]> stringEntry : request.getParameterMap().entrySet()) {
                if (stringEntry.getKey().startsWith(SearchConstant.DYNAMIC_SEARCH_PREFIX)) {
                    q.add(sqlQueryParameterInterpreter.invoke(stringEntry.getKey(), stringEntry.getValue()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return q;
    }
}
