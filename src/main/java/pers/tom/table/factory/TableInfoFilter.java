package pers.tom.table.factory;

import pers.tom.table.TableInfo;

/**
 * @author lijia
 * @description 表信息过滤器
 * @date 2021-07-28 15:34
 */
public interface TableInfoFilter {

    TableInfoFilter DEFAULT_FILTER = tableInfo -> true;

    /**
     * 过滤逻辑
     * @param tableInfo table info
     * @return boolean
     */
    boolean doFilter(TableInfo tableInfo);
}
