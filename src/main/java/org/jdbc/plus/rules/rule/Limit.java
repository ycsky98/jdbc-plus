package org.jdbc.plus.rules.rule;

import java.util.Map;

import org.jdbc.plus.rules.pojo.Type;

public class Limit {

    private Logic logic;

    public Limit(Logic logic) {
        this.logic = logic;
    }

    /**
     * 结束用语
     * 
     * @return
     */
    public Logic end() {
        return this.logic;
    }

    /**
     * 返回条件数据
     * 
     * @return
     */
    public Map<String, Type> buildParam() {
        return this.logic.paramLogic;
    }
}
