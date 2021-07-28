package pers.tom.table.factory;

import pers.tom.table.TableInfos;


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
    default TableInfos get(){
        return get(TableInfoFilter.DEFAULT_FILTER);
    }

    /**
     * 获取指定的表信息
     *
     * @param filter table过滤器
     * @return table info
     */
    TableInfos get(TableInfoFilter filter);
}
