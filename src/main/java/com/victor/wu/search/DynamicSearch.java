package com.victor.wu.search;

import com.victor.wu.constant.SearchConstant;
import com.victor.wu.query.Q;
import com.victor.wu.search.interpreter.quick.QuickQueryParameterInterpreter;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
