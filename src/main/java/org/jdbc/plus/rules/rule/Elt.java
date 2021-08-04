package org.jdbc.plus.rules.rule;

import org.jdbc.plus.rules.pojo.Type;
import org.jdbc.plus.rules.whereType.WhereType;

/**
 * @author yangcong
 * 
 *         小于等于
 */
public class Elt {

    private Logic logic;

    public Elt(Logic logic) {
        this.logic = logic;
    }

    public Logic elt(String column, Object value) {
        this.logic.paramLogic.put(column, new Type().setValue(value).setType(WhereType.ELT));
        return this.logic;
    }

}
