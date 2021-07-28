package org.jdbc.plus.rules.build;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.jdbc.plus.rules.pojo.Type;
import org.jdbc.plus.rules.rule.Logic;
import org.jdbc.plus.rules.whereType.WhereType;

/**
 * @author yangcong
 * 
 *         sql生成
 */
public class SqlWhereBuild {

    /**
     * 根据逻辑构建sql
     * 
     * @param logic
     * @return {@link java.sql.PreparedStatement}
     * 
     * @throws {@link java.sql.SQLException}
     */
    public PreparedStatement sqlBuild(String befsql, Logic logic, Connection connection) throws SQLException {
        StringBuffer sql = new StringBuffer();
        // 获取构建好的条件值
        Map<String, Type> valueType = logic.buildParam();
        PreparedStatement preparedStatement = null;
        int size = valueType.size();
        // 如果条件不为空
        if (valueType.size() > 0) {
            sql.append(" WHERE ");
            // 构建sql语句
            int count = 1;
            for (Map.Entry<String, Type> entry : valueType.entrySet()) {
                WhereType whereType = entry.getValue().getType();
                switch (whereType) {
                    case EQ:
                        sql.append(entry.getKey()).append(" = ").append("?").append(" ");
                        if (!checkEnd(count, size)) {
                            sql.append("AND ");
                        }
                        break;
                    case LT:
                        sql.append(entry.getKey()).append(" < ").append("?").append(" ");
                        if (!checkEnd(count, size)) {
                            sql.append("AND ");
                        }
                        break;
                    case GT:
                        sql.append(entry.getKey()).append(" > ").append("?").append(" ");
                        if (!checkEnd(count, size)) {
                            sql.append("AND ");
                        }
                        break;
                    default:
                        break;
                }
            }
            preparedStatement = connection.prepareStatement(befsql + sql.toString());
            count = 1;
            for (Map.Entry<String, Type> entry : valueType.entrySet()) {
                preparedStatement.setObject(count, entry.getValue().getValue());
            }
        } else {// 没有任何逻辑执行原sql
            preparedStatement = connection.prepareStatement(befsql);
        }
        return preparedStatement;
    }

    /**
     * 判断是否为底部
     * 
     * @param count
     * @param size
     * @return
     */
    private boolean checkEnd(int count, int size) {
        return count == size;
    }
}
