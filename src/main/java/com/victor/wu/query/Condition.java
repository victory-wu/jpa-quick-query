package com.victor.wu.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IDEA
 * ProjectName: server
 * Date: 10/31/18 5:18 PM
 * @author wzx
 * @version 1.0
 */

@Getter
@Setter
public class Condition extends BaseCondition {

    private String field;
    private Object value;
    private boolean ignoreNull;
    private ConditionType expressions;

    /**
     *
     * @param field
     * @param expressions
     * @param value
     * @param ignoreNull 是否忽略空值
     */
    Condition(String field, ConditionType expressions, Object value, boolean ignoreNull) {
        this.field = field;
        this.value = value;
        this.ignoreNull = ignoreNull;
        this.expressions = expressions;
    }

    public static Condition lk(String field, String value, boolean ignoreNull){
        return new Condition(field, ConditionType.like, value, ignoreNull);
    }

    public static Condition nlk(String field, String value, boolean ignoreNull){
        return new Condition(field, ConditionType.notLike, value, ignoreNull);
    }

    public static Condition eq(String field, Object value, boolean ignoreNull){
        return new Condition(field, ConditionType.equals, value, ignoreNull);
    }

    public static Condition neq(String field, Object value, boolean ignoreNull){
        return new Condition(field, ConditionType.notEquals, value, ignoreNull);
    }

    public static Condition lt(String field, Object value, boolean ignoreNull){
        return new Condition(field, ConditionType.lt, value, ignoreNull);
    }
    public static Condition gt(String field, Object value, boolean ignoreNull){
        return new Condition(field, ConditionType.gt, value, ignoreNull);
    }

}
