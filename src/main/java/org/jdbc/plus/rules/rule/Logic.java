package org.jdbc.plus.rules.rule;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jdbc.plus.rules.pojo.Type;
import org.jdbc.plus.rules.whereType.WhereType;

/**
 * @author yangcong 逻辑开启
 */
public class Logic {

    /**
     * 用于装取逻辑
     */
    protected Map<String, Type> paramLogic = Collections.synchronizedMap(new LinkedHashMap<>(16));

    /**
     * 等于
     * 
     * @param column
     * @param value
     * @return
     */
    public Eq eq(String column, Object value) {
        this.paramLogic.put(column, new Type().setValue(value).setType(WhereType.EQ));
        return new Eq(this);
    }

    /**
     * 小于
     * 
     * @param column
     * @param value
     * @return
     */
    public Lt lt(String column, Object value) {
        this.paramLogic.put(column, new Type().setValue(value).setType(WhereType.LT));
        return new Lt(this);
    }

    /**
     * 返回条件数据
     * 
     * @return
     */
    public Map<String, Type> buildParam() {
        return this.paramLogic;
    }
}
