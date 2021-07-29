package table.factory.jdbc.dialect;

/**
 * @author lijia
 * @description mysql数据库方言
 * @date 2021-07-28 11:56
 */
public class MysqlDialect implements Dialect {

    @Override
    public String showTablesSql(String databaseName) {
        return "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '" + databaseName + "'";
    }

    @Override
    public String showColumnsSql(String databaseName, String tableName) {
        return "SHOW FULL FIELDS FROM `" + databaseName + "`.`" + tableName + "`";
    }

    @Override
    public String getTableNameField() {
        return "TABLE_NAME";
    }

    @Override
    public String getTableCommentField() {
        return "TABLE_COMMENT";
    }

    @Override
    public String getColumnNameField() {
        return "FIELD";
    }

    @Override
    public String getColumnTypeField() {
        return "TYPE";
    }

    @Override
    public String getColumnCommentField() {
        return "COMMENT";
    }

    @Override
    public String getColumnKeyField() {
        return "KEY";
    }

    @Override
    public boolean isPrimary(String key) {
        return "PRI".equals(key);
    }
}
