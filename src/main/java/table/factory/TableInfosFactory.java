package table.factory;


import table.TableInfo;

import java.util.List;

/**
 * @author lijia
 * @description 表信息工厂
 * @date 2021-07-28 11:06
 */
public interface TableInfosFactory {

    /**
     * 获取所有的表
     *
     * @return table infos
     */
    default List<TableInfo> get(){
        return get(TableInfoFilter.DEFAULT_FILTER);
    }

    /**
     * 获取指定的表信息
     *
     * @param filter table过滤器
     * @return table info
     */
    List<TableInfo> get(TableInfoFilter filter);
}
