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

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * 根据逻辑构建sql
     * 
     * @param logic
     * @return {@link java.sql.PreparedStatement}
     * 
     * @throws {@link java.sql.SQLException}
     */
    public PreparedStatement sqlBuild(String befsql, Logic logic) throws SQLException {
        StringBuffer sql = new StringBuffer();
        sql.append(befsql);
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
                    case EQ:// 等于
                        sql.append(entry.getKey()).append(WhereType.EQ.getSymbol()).append("?").append(" ");
                        if (!checkEnd(count, size)) {
                            sql.append("AND ");
                        }
                        break;
                    case LT:// 小于
                        sql.append(entry.getKey()).append(WhereType.LT.getSymbol()).append("?").append(" ");
                        if (!checkEnd(count, size)) {
                            sql.append("AND ");
                        }
                        break;
                    case GT:// 大于
                        sql.append(entry.getKey()).append(WhereType.GT.getSymbol()).append("?").append(" ");
                        if (!checkEnd(count, size)) {
                            sql.append("AND ");
                        }
                        break;
                    case LIMIT:
                        // limit到末尾
                        // 剔除上一次AND
                        sql.replace(sql.lastIndexOf("AND"), sql.lastIndexOf("AND") + "AND ".length(), "");
                        String[] args = ((String) entry.getValue().getValue()).split("&");
                        sql.append(WhereType.LIMIT.getSymbol()).append(Integer.parseInt(args[0])).append(", ")
                                .append(args[1]);
                        // 不需要判定是否需要加END
                        break;
                    default:
                        break;
                }
                count++;
            }
            preparedStatement = this.connection.prepareStatement(sql.toString());
            count = 1;
            for (Map.Entry<String, Type> entry : valueType.entrySet()) {
                preparedStatement.setObject(count, entry.getValue().getValue());
            }
        } else {// 没有任何逻辑执行原sql
            preparedStatement = this.connection.prepareStatement(befsql);
        }
        return preparedStatement;
    }

    /**
     * 根据逻辑构建sql
     * 
     * @param valueType
     * @return {@link java.sql.PreparedStatement}
     * 
     * @throws {@link java.sql.SQLException}
     */
    public PreparedStatement sqlBuild(String befsql, Map<String, Type> valueType) throws SQLException {
        StringBuffer sql = new StringBuffer();
        sql.append(befsql);
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
                    case EQ:// 等于
                        sql.append(entry.getKey()).append(WhereType.EQ.getSymbol()).append("?").append(" ");
                        if (!checkEnd(count, size)) {
                            sql.append("AND ");
                        }
                        break;
                    case LT:// 小于
                        sql.append(entry.getKey()).append(WhereType.LT.getSymbol()).append("?").append(" ");
                        if (!checkEnd(count, size)) {
                            sql.append("AND ");
                        }
                        break;
                    case GT:// 大于
                        sql.append(entry.getKey()).append(WhereType.GT.getSymbol()).append("?").append(" ");
                        if (!checkEnd(count, size)) {
                            sql.append("AND ");
                        }
                        break;
                    case LIMIT:
                        // limit到末尾
                        // 剔除上一次AND
                        sql.replace(sql.lastIndexOf("AND"), sql.lastIndexOf("AND") + "AND ".length(), "");
                        String[] args = ((String) entry.getValue().getValue()).split("&");
                        sql.append(WhereType.LIMIT.getSymbol()).append(Integer.parseInt(args[0])).append(", ")
                                .append(args[1]);
                        // 不需要判定是否需要加END
                        break;
                    default:
                        break;
                }
                count++;
            }
            preparedStatement = this.connection.prepareStatement(sql.toString());
            count = 1;
            for (Map.Entry<String, Type> entry : valueType.entrySet()) {
                preparedStatement.setObject(count, entry.getValue().getValue());
            }
        } else {// 没有任何逻辑执行原sql
            preparedStatement = this.connection.prepareStatement(befsql);
        }
        return preparedStatement;
    }

    /**
     * 判断是否为底部
     * 
     * @param count
     * @param size
     * @return {@link java.lang.Boolean}
     */
    private boolean checkEnd(int count, int size) {
        return count == size;
    }
}
