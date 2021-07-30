package pers.tom.table.factory.jdbc.dialect;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lijia
 * @description mysql数据库方言
 * @date 2021-07-28 11:56
 */
public class MysqlDialect implements Dialect {

    /**缓存类型映射*/
    private Map<String, Class<?>> typeMappings;

    public MysqlDialect(){
        initTypeMappings();
    }

    @Override
    public String showTablesSql(String databaseName) {
        String from = databaseName == null ? "" : " FROM " + databaseName;
        return "SHOW TABLE STATUS" + from;
    }

    @Override
    public String showColumnsSql(String databaseName, String tableName) {
        String from = databaseName == null ? "" : " FROM " + databaseName;
        return "SHOW FULL FIELDS FROM " + tableName + from;
    }

    @Override
    public String getTableNameField() {
        return "NAME";
    }

    @Override
    public String getTableCommentField() {
        return "COMMENT";
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

    @Override
    public Class<?> getJavaType(String type) {
        return typeMappings.get(type);
    }

    @Override
    public void registerTypeMapping(String type, Class<?> javaType) {
        if (typeMappings == null) {
            typeMappings = new HashMap<>();
        }
        typeMappings.put(type, javaType);
    }

    /**
     * 初始化类型映射
     */
    private void initTypeMappings() {
        registerTypeMapping("int", int.class);
        registerTypeMapping("varchar", String.class);
        registerTypeMapping("date", Date.class);
        registerTypeMapping("datetime", Date.class);
        registerTypeMapping("timestamp", Timestamp.class);
        registerTypeMapping("char", String.class);
        registerTypeMapping("text", String.class);
        registerTypeMapping("longtext", String.class);
        registerTypeMapping("mediumtext", String.class);
        registerTypeMapping("longvarchar", String.class);
        registerTypeMapping("numeric", BigDecimal.class);
        registerTypeMapping("decimal", BigDecimal.class);
        registerTypeMapping("bit", boolean.class);
        registerTypeMapping("tinyint", byte.class);
        registerTypeMapping("smallint", short.class);
        registerTypeMapping("integer", int.class);
        registerTypeMapping("bigint", long.class);
        registerTypeMapping("real", float.class);
        registerTypeMapping("float", float.class);
        registerTypeMapping("double", double.class);
        registerTypeMapping("binary", byte[].class);
        registerTypeMapping("time", Time.class);
    }

}
