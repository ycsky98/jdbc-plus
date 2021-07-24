package org.jdbc.plus.rules.rule;

import org.jdbc.plus.rules.pojo.Type;
import org.jdbc.plus.rules.whereType.WhereType;

/**
 * @author yangcong
 * 
 *         eq比较
 */
public class Eq {

    /**
     * 存储上一份链式节点
     */
    private Logic logic;

    public Eq(Logic logic) {
        this.logic = logic;
    }

    /**
     * eq 比较
     * 
     * @param column
     * @param value
     * @return
     */
    public Eq eq(String column, Object value) {
        this.logic.paramLogic.put(column, new Type().setValue(value).setType(WhereType.EQ));
        return this;
    }

}
