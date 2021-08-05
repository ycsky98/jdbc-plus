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
     * 用于装取WHERE逻辑
     */
    protected volatile Map<String, Type> paramLogic = Collections.synchronizedMap(new LinkedHashMap<>(16));

    /**
     * 用于存储Update set值
     */
    protected volatile Map<String, Object> setLogic = Collections.synchronizedMap(new LinkedHashMap<>(16));

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
     * 大于等于
     * 
     * @param column
     * @param value
     * @return
     */
    public Egt egt(String column, Object value) {
        this.paramLogic.put(column, new Type().setValue(value).setType(WhereType.EGT));
        return new Egt(this);
    }

    /**
     * 小于等于
     * 
     * @param column
     * @param value
     * @return
     */
    public Elt elt(String column, Object value) {
        this.paramLogic.put(column, new Type().setValue(value).setType(WhereType.ELT));
        return new Elt(this);
    }

    public Limit limit(Integer start, Integer end) {
        this.paramLogic.put("&LIMIT&", new Type().setType(WhereType.LIMIT).setValue(start + "&" + end));
        return new Limit(this);
    }

    /**
     * Update情况下的set值操作
     * 
     * @param column
     * @param value
     * @return
     */
    public Logic set(String column, Object value) {
        this.setLogic.put(column, value);
        return this;
    }

    /**
     * 结束用语
     * 
     * @return
     */
    public Logic end() {
        return this;
    }

    /**
     * 返回条件数据
     * 
     * @return
     */
    public Map<String, Type> buildParam() {
        return this.paramLogic;
    }

    /**
     * 返回set数据
     * 
     * @return
     */
    public Map<String, Object> buildSetParam() {
        return this.setLogic;
    }
}
