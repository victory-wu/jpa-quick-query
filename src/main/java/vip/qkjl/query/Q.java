package vip.qkjl.query;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 * ProjectName: server
 * Date: 10/31/18 5:18 PM
 * @author wzx
 * @version 1.0
 */

public class Q<T> implements Specification<T> {
    List<BaseCondition> expressionList = new ArrayList<>();

    public Q() {
        //add(Condition.eq("1", "1", false));
    }

    public Q where(){
        return this;
    }

    public Q where(Condition condition){
        and(condition);
        return this;
    }

    public Q add(Condition condition){
        and(condition);
        return this;
    }

    public Q and(Condition... conditions){
        ConditionGroup cg = ConditionGroup.AND();
        for (Condition item : conditions) {
            cg.add(item);
        }
        expressionList.add(cg);
        return this;
    }
    public Q or(Condition... conditions){
        ConditionGroup cg = ConditionGroup.OR();
        for (Condition item : conditions) {
            cg.add(item);
        }
        expressionList.add(cg);
        return this;
    }


    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        List<Expression<Boolean>> list = predicate.getExpressions();
        for (BaseCondition baseCondition: expressionList) {
            if (baseCondition instanceof ConditionGroup) {

                ConditionGroup conditionGroup = (ConditionGroup) baseCondition;
                Predicate[] result = parseConditionGroup((ConditionGroup) baseCondition, root, cq, cb);
                if (result != null && result.length > 0) {
                    if (conditionGroup.getConditionAndOr().equals(ConditionAndOr.or)) {
                        list.add(cb.or(result));
                    } else {
                        list.add(cb.and(result));
                    }
                }

            } else if (baseCondition instanceof Condition) {
                Condition condition = (Condition) baseCondition;
                if(condition.isIgnoreNull() && condition.getValue() == null){
                    continue;
                }
                list.add(cb.and(parseCondition(condition, root, cq, cb)));
            }
        }
        return predicate;
    }

    public Predicate[] parseConditionGroup(ConditionGroup conditionGroup, Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        List<Predicate> list = new ArrayList<>();
        Predicate[] predicates;
        for (BaseCondition baseCondition : conditionGroup.getList()) {
            if (baseCondition instanceof ConditionGroup) {

                if(conditionGroup.getConditionAndOr().equals(ConditionAndOr.or)){
                    list.add(cb.or(parseConditionGroup((ConditionGroup) baseCondition, root, cq, cb)));
                } else {
                    list.add(cb.and(parseConditionGroup((ConditionGroup) baseCondition, root, cq, cb)));
                }

            } else if (baseCondition instanceof Condition) {
                Condition condition = (Condition) baseCondition;
                if(condition.isIgnoreNull() && condition.getValue() == null){
                    continue;
                }

                if(conditionGroup.getConditionAndOr().equals(ConditionAndOr.or)){
                    list.add(cb.or(parseCondition(condition, root, cq, cb)));
                } else {
                    list.add(cb.and(parseCondition(condition, root, cq, cb)));
                }

            }
        }
        predicates = new Predicate[list.size()];
        list.toArray(predicates);
        return predicates;
    }

    public Predicate parseCondition(Condition condition, Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        Predicate predicate = null;
        switch (condition.getExpressions()){
            case like:
                predicate = (cb.like((Expression<String>) getFieldPath(root, condition.getField()), "%" + condition.getValue() + "%"));
                break;
            case notLike:
                predicate = (cb.notLike((Expression<String>) getFieldPath(root, condition.getField()), (String) condition.getValue()));
                break;
            case gt:
                if(condition.getValue() instanceof Number) {
                    predicate = (cb.gt((Expression<? extends Number>) getFieldPath(root, condition.getField()), (Number) condition.getValue()));
                } else {
                    predicate = (cb.greaterThan(getFieldPath(root, condition.getField()), (Comparable) condition.getValue()));
                }
                break;
            case lt:
                if(condition.getValue() instanceof Number) {
                    predicate = (cb.lt((Expression<? extends Number>) getFieldPath(root, condition.getField()), (Number) condition.getValue()));
                } else {
                    predicate = (cb.lessThan(getFieldPath(root, condition.getField()), (Comparable) condition.getValue()));
                }
                break;
            case equals:
                predicate = (cb.equal(getFieldPath(root, condition.getField()), condition.getValue()));
                break;
            case notEquals:
                predicate = (cb.notEqual(getFieldPath(root, condition.getField()), condition.getValue()));
                break;
        }
        return predicate;
    }

    public Expression getFieldPath(Root<T> root, String fieldName) {
        if (fieldName.indexOf('.') == -1) {
            return root.get(fieldName);
        }
        String[] fields = fieldName.split("\\.");
        Path path = root.get(fields[0]);
        for (int i = 1; i < fields.length; i++) {
            path = path.get(fields[i]);
        }
        return path;
    }

}













