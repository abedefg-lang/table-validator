package pers.tom.table.factory.jdbc;

import lombok.Data;
import lombok.experimental.Accessors;
import pers.tom.table.TableInfo;
import pers.tom.table.factory.TableInfoFilter;
import pers.tom.table.utils.StringArrayUtils;


/**
 * @author lijia
 * @description 表名过滤器
 * @date 2021-07-28 15:41
 */
@Data
@Accessors(chain = true)
public class TableNameFilter implements TableInfoFilter {

    /**符合条件的正则*/
    private String[] includeRegex;

    /**需要排除的正则*/
    private String[] excludeRegex;

    @Override
    public boolean doFilter(TableInfo tableInfo) {
        if(tableInfo == null){
            return false;
        }
        String tableName = tableInfo.getName();
        // 当满足在include中 并且 不在exclude中
        return StringArrayUtils.matches(tableName, includeRegex) && !StringArrayUtils.matches(tableName, excludeRegex);
    }

    public static TableNameFilter ofIncludeAll(){
        return ofIncludeRegex(".*");
    }

    public static TableNameFilter ofIncludeRegex(String... includeRegex){
        TableNameFilter filter = new TableNameFilter();
        return filter.setIncludeRegex(includeRegex);
    }
}
