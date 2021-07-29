package table.factory.entity.parser;


import table.TableInfo;

/**
 * @author lijia
 * @description 解析器 将实体类解析成table info
 * @date 2021-07-28 16:32
 */
public interface EntityTableInfoParser {

    /**
     * 解析逻辑
     *
     * @param entityClass 实体类class
     * @return table info
     */
    TableInfo parse(Class<?> entityClass);

}
