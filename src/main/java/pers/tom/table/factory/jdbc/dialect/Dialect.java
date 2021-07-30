package pers.tom.table.factory.jdbc.dialect;

/**
 * @author lijia
 * @description 数据库方言   不同的数据库产品对与以下的操作会有区别
 *              查询表信息sql
 *              查询字段sql
 *              类型转换
 * @date 2021-07-28 11:54
 */
public interface Dialect {

    /**
     * 获取查询表信息的sql
     *
     * @param databaseName 指定数据库 可以为null
     * @return 返回sql
     */
    String showTablesSql(String databaseName);

    /**
     * 获取查询指定表下面的字段信息的sql
     *
     * @param databaseName 指定数据库 可以为null
     * @param tableName 指定表名
     * @return 返回sql
     */
    String showColumnsSql(String databaseName, String tableName);

    /**
     * 获取查询表名称的字段
     *
     * @return 返回字段
     */
    String getTableNameField();

    /**
     * 获取查询表注释的字段
     *
     * @return 返回字段
     */
    String getTableCommentField();

    /**
     * 获取查询字段名称的字段
     *
     * @return 返回字段
     */
    String getColumnNameField();

    /**
     * 获取查询字段类型名称的字段
     *
     * @return 返回字段
     */
    String getColumnTypeField();

    /**
     * 获取查询字段注释的字段
     *
     * @return 返回字段
     */
    String getColumnCommentField();

    /**
     * 获取查询字段key的字段
     *
     * @return 返回字段
     */
    String getColumnKeyField();

    /**
     * 判断某个key是否是主键
     *
     * @param key 主键
     * @return 返回boolean
     */
    boolean isPrimary(String key);

    /**
     * 获取字段类型对应的java类型
     * @param type 字段类型
     * @return java type
     */
    Class<?> getJavaType(String type);

    /**
     * 添加类型映射  如果存在会直接覆盖
     * @param type 字段类型
     * @param javaType java type
     */
    void registerTypeMapping(String type, Class<?> javaType);
}
