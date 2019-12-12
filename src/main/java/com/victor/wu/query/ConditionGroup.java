package com.victor.wu.query;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 * ProjectName: server
 * Date: 10/31/18 5:18 PM
 * @author wzx
 * @version 1.0
 */

@Getter
public class ConditionGroup extends BaseCondition {
    private List<BaseCondition> list = new ArrayList<>();
    private ConditionAndOr conditionAndOr;

    private ConditionGroup(ConditionAndOr conditionAndOr){
        this.conditionAndOr = conditionAndOr;
    }

    public static ConditionGroup OR(){
        return new ConditionGroup(ConditionAndOr.or);
    }

    public static ConditionGroup AND(){
        return new ConditionGroup(ConditionAndOr.or);
    }

    public void add(BaseCondition condi){
        list.add(condi);
    }

    public void setConditionAndOr(ConditionAndOr conditionAndOr) {
        this.conditionAndOr = conditionAndOr;
    }
}
