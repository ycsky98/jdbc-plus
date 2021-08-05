package org.jdbc.plus.rules.whereType;

/**
 * @author yangcong
 * 
 *         逻辑类型
 */
public enum WhereType {
    // = lt gt noeq limit
    EQ(" = "), LT(" < "), GT(" > "), NOEQ(" != "), EGT(" >= "), ELT(" <= "), LIMIT(" LIMIT ");

    private String symbol;

    private WhereType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
