package pers.tom.table.factory.jdbc;


import lombok.Data;
import lombok.experimental.Accessors;
import pers.tom.table.ColumnInfo;
import pers.tom.table.TableInfo;
import pers.tom.table.TableInfos;
import pers.tom.table.converter.DbTypeConverter;
import pers.tom.table.converter.JavaTypeConverter;
import pers.tom.table.factory.TableInfoFilter;
import pers.tom.table.factory.TableInfosFactory;
import pers.tom.table.factory.jdbc.dialect.Dialect;
import pers.tom.table.factory.jdbc.dialect.Dialects;
import pers.tom.table.utils.NamingUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lijia
 * @description 基于jdbc的table infos工厂
 * @date 2021-07-28 11:49
 */
@Data
@Accessors(chain = true)
public class JdbcTableInfosFactory implements TableInfosFactory {

    /**
     * 数据源
     */
    private final DataSource dataSource;

    /**
     * 使用的数据库方言
     */
    private final Dialect dialect;

    /**
     * 数据库名称
     */
    private String databaseName;

    /**
     * 类型处理器
     */
    private JavaTypeConverter typeConverter;

    /**
     * 是否转换成驼峰式 默认为true
     */
    private boolean toCamelCase;


    public JdbcTableInfosFactory(DataSource dataSource) {
        // 默认使用mysql方言
        this(dataSource, Dialects.MYSQL.getDialect());
    }

    public JdbcTableInfosFactory(DataSource dataSource, Dialect dialect) {
        this.dataSource = dataSource;
        this.dialect = dialect;
        this.typeConverter = new DbTypeConverter();
        this.toCamelCase = true;
    }


    @Override
    public TableInfos get(TableInfoFilter filter) {

        return get(databaseName, filter);
    }

    /**
     * 获取指定数据库 下面符合条件的表
     *  1，获取所有table
     *  2，过滤table
     *  3，为已过滤的table设置columns
     *
     * @param databaseName 数据库名
     * @param filter 过滤器
     * @return table info
     */
    public TableInfos get(String databaseName, TableInfoFilter filter){

        // 检查参数和属性
        Objects.requireNonNull(dataSource, "datasource不能为null");
        Objects.requireNonNull(dialect, "dialect不能为null");
        Objects.requireNonNull(filter, "filter不能为null");
        Objects.requireNonNull(databaseName, "databaseName不能为null");

        List<TableInfo> filteredTableInfos = new ArrayList<>();
        try {
            // 获取表信息
            List<TableInfo> allTableInfos = getAllTableInfos(databaseName);
            String tableName;
            for (TableInfo info : allTableInfos) {
                tableName = info.getName();
                if (filter.doFilter(info)) {
                    // 设置字段信息
                    info.setColumns(getColumns(databaseName, tableName));
                    filteredTableInfos.add(info);
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return new TableInfos(filteredTableInfos);
    }


    /**
     * 获取所有的table info
     *
     * @param databaseName 指定数据库
     * @return table infos
     */
    private List<TableInfo> getAllTableInfos(String databaseName) throws SQLException {

        String sql = dialect.showTablesSql(databaseName);
        List<TableInfo> tableInfos = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            // 创建table info
            while (resultSet.next()) {
                // 获取表信息
                String tableName = resultSet.getString(dialect.getTableNameField());
                String className = toCamelCase ? NamingUtils.toGreatCamelCase(tableName, "_") : NamingUtils.initialUppercase(tableName);
                String comment = resultSet.getString(dialect.getTableCommentField());
                // 添加table info
                tableInfos.add(new TableInfo(tableName, className).setComment(comment));
            }
        }
        return tableInfos;
    }

    /**
     * 获取指定表的字段信息
     *
     * @param databaseName 指定数据库
     * @param tableName 表名
     * @return columns
     */
    private List<ColumnInfo> getColumns(String databaseName, String tableName) throws SQLException {

        List<ColumnInfo> columns = new ArrayList<>();
        String sql = dialect.showColumnsSql(databaseName, tableName);
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            // 将结果封装成字段信息
            while (resultSet.next()) {
                // 获取字段信息
                String columnName = resultSet.getString(dialect.getColumnNameField());
                String fieldName = toCamelCase ? NamingUtils.toCamelCase(columnName, "_") : columnName;
                String key = resultSet.getString(dialect.getColumnKeyField());
                boolean isPrimary = dialect.isPrimary(key);
                String columnType = resultSet.getString(dialect.getColumnTypeField());
                String comment = resultSet.getString(dialect.getColumnCommentField());
                Class<?> javaType = typeConverter.convert(columnType);
                // 添加字段
                columns.add(new ColumnInfo(columnName, fieldName, columnType, javaType, comment, key, isPrimary));
            }
        }
        return columns;
    }

}