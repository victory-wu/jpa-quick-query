package com.victor.wu.search;

import com.victor.wu.constant.SearchConstant;
import com.victor.wu.query.Q;
import com.victor.wu.search.interpreter.quick.QuickQueryParameterInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 动态搜索：支持规则
 * 参数用 下划线分割 _ 例如：S_属性名_LK_A   S_username_EQ_S
 *
 *
 *
 *
 *
 */
@Component
public class DynamicSearch {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private QuickQueryParameterInterpreter sqlQueryParameterInterpreter;

    public Q getQ() {
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
