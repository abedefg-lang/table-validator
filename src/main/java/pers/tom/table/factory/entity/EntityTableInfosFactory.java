package pers.tom.table.factory.entity;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.ClassScanner;
import pers.tom.table.TableInfo;
import pers.tom.table.TableInfos;
import pers.tom.table.factory.TableInfoFilter;
import pers.tom.table.factory.TableInfosFactory;
import pers.tom.table.factory.entity.parser.EntityTableInfoParser;

import java.util.*;

/**
 * @author lijia
 * @description 通过解析实体类获取table info
 * @date 2021-07-28 16:29
 */
public class EntityTableInfosFactory implements TableInfosFactory {

    /**扫包路径 默认扫描根路径*/
    private final String basePackageName;

    /**解析器*/
    private final EntityTableInfoParser parser;

    public EntityTableInfosFactory(EntityTableInfoParser parser){
        this("", parser);
    }

    public EntityTableInfosFactory(String basePackageName, EntityTableInfoParser parser) {
        this.basePackageName = basePackageName;
        this.parser = parser;
    }

    @Override
    public TableInfos get(TableInfoFilter filter) {

        // 检查参数属性
        Objects.requireNonNull(parser, "parser不能为null");
        Objects.requireNonNull(filter, "filter不能为null");

        // 扫描class
        final Set<Class<?>> classSet = ClassScanner.scanPackage(basePackageName);
        if(CollectionUtil.isEmpty(classSet)){
            return new TableInfos(Collections.emptyList());
        }
        // 循环解析table info
        List<TableInfo> tableInfos = new ArrayList<>(classSet.size());
        TableInfo table;
        for(Class<?> entityClass : classSet){
            table = parser.parse(entityClass);
            if(table != null && filter.doFilter(table)){
                tableInfos.add(table);
            }
        }
        return new TableInfos(tableInfos);
    }
}
