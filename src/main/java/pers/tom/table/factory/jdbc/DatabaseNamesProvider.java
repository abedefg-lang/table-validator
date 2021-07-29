package pers.tom.table.factory.jdbc;

import java.util.Set;

/**
 * @author lijia
 * @description 数据库名称provider
 * @date 2021-07-29 14:38
 */
public interface DatabaseNamesProvider {

    /**
     * 获取数据库名称
     * @return database names
     */
    Set<String> get();
}
