package org.jdbc.plus.rules.rule;

import org.jdbc.plus.rules.pojo.Type;
import org.jdbc.plus.rules.whereType.WhereType;

/**
 * @author yangcong
 * 
 *         小于
 */
public class Lt {

    private Logic logic;

    public Lt(Logic logic) {
        this.logic = logic;
    }

    public Logic lt(String column, Object value) {
        this.logic.paramLogic.put(column, new Type().setValue(value).setType(WhereType.LT));
        return this.logic;
    }
}
