package org.jdbc.plus.rules.rule;

import org.jdbc.plus.rules.pojo.Type;
import org.jdbc.plus.rules.whereType.WhereType;

/**
 * @author yangcong
 * 
 *         大于等于
 */
public class Egt {

    private Logic logic;

    public Egt(Logic logic) {
        this.logic = logic;
    }

    public Logic egt(String column, Object value) {
        this.logic.paramLogic.put(column, new Type().setValue(value).setType(WhereType.EGT));
        return this.logic;
    }

    /**
     * 结束
     * 
     * @return
     */
    public Logic end() {
        return this.logic;
    }

}
