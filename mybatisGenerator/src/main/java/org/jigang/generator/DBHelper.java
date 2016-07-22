package org.jigang.generator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujigang on 16/7/15.
 */
public class DBHelper {




    public static List<ColumnInfo> getAllColumns(String schemaName, String tableName) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            String driver = PropertiesHelper.getProps("mysql.driver");
            String url = PropertiesHelper.getProps("mysql.url");
            String user = PropertiesHelper.getProps("mysql.username");
            String password = PropertiesHelper.getProps("mysql.password");

            Class.forName(driver);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            String sql = "select COLUMN_NAME AS name, DATA_TYPE AS type, CHARACTER_MAXIMUM_LENGTH AS len, IS_NULLABLE AS isNullable, " +
                    "COLUMN_KEY AS columnKey, ORDINAL_POSITION as ordinalPosition from information_schema.COLUMNS " +
                    "where TABLE_SCHEMA = ? AND TABLE_NAME = ? ORDER BY ORDINAL_POSITION";
            pst = conn.prepareStatement(sql);//准备执行语句
            pst.setString(1, schemaName);
            pst.setString(2, tableName);
            ResultSet resultSet = pst.executeQuery();
            return resolveResult(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (null != pst) {
                    pst.close();
                }
                if (null != conn) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<ColumnInfo> resolveResult(ResultSet resultSet) throws SQLException {
        if (null != resultSet) {
            List<ColumnInfo> columns = new ArrayList<ColumnInfo>();
            while (resultSet.next()) {
                ColumnInfo column = new ColumnInfo();
                column.setName(resultSet.getString("name"));
                column.setType(resultSet.getString("type"));
                column.setLen(resultSet.getInt("len"));
                column.setIsNullable(resultSet.getString("isNullable"));
                column.setColumnKey(resultSet.getString("columnKey"));
                column.setOrdinalPosition(resultSet.getInt("ordinalPosition"));
                columns.add(column);
            }
            return columns;
        }
        return null;
    }
}
