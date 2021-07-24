package org.jdbc.plus.rules.pojo;

import org.jdbc.plus.rules.whereType.WhereType;

/**
 * @author yangcong
 * 
 *         逻辑类型数据存储
 */
public class Type {

    private WhereType type;

    private Object value;

    public WhereType getType() {
        return type;
    }

    public Type setType(WhereType type) {
        this.type = type;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public Type setValue(Object value) {
        this.value = value;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Type other = (Type) obj;
        if (type != other.type)
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Type [type=%s, value=%s]", type, value);
    }

}
