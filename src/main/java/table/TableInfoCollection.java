package table;

import lombok.Data;
import table.validation.ValidationResult;
import table.validation.ValidationResultImpl;
import table.validation.error.ExtraColumnValidationError;
import table.validation.error.MismatchColumnValidationError;
import table.validation.error.MissingColumnValidationError;
import table.validation.error.MissingTableValidationError;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lijia
 * @description
 * @date 2021-07-28 17:17
 */
@Data
public class TableInfoCollection {

    /**集合名称*/
    private final String name;

    /**table list*/
    private final List<TableInfo> tableInfos;

    public TableInfoCollection(String name, List<TableInfo> tableInfos){
        this.name = name;
        this.tableInfos = tableInfos;
    }

    /**
     * 验证当前表集合是否包含其他表集合
     * 包含是指 this必须含有other中的所有table  两个table的字段必须完全相同
     *
     * @param other other
     * @return 返回验证结果
     */
    public ValidationResult validationContains(TableInfoCollection other) {

        ValidationResult result = new ValidationResultImpl();
        // 将结构转换为map 可以为表名
        Map<String, TableInfo> tableMap = getTableInfos().stream().collect(Collectors.toMap(TableInfo::getName, o -> o));
        for (TableInfo otherTable : other.getTableInfos()) {
            // 验证表是否存在
            TableInfo table = tableMap.get(otherTable.getName());
            if (table == null) {
                // 添加缺少table错误信息
                result.addError(new MissingTableValidationError(name, otherTable));
                continue;
            }
            // 验证table中的字段
            Map<String, ColumnInfo> columnMap = table.getColumns().stream().collect(Collectors.toMap(ColumnInfo::getName, o -> o));
            Set<String> existsColumnNameSet = new HashSet<>();// 记录当前存在的字段名 用来判断多余的字段
            for(ColumnInfo otherColumn : otherTable.getColumns()){
                // 验证字段是否存在
                ColumnInfo column = columnMap.get(otherColumn.getName());
                if(column == null){
                    // 添加缺少字段错误信息
                    result.addError(new MissingColumnValidationError(name, table, otherColumn));
                    continue;
                }
                // 添加存在字段
                existsColumnNameSet.add(column.getName());
                // 验证字段是否匹配
                if(!column.equals(otherColumn)){
                    result.addError(new MismatchColumnValidationError(name, table, column, otherColumn));
                }
            }
            // 判断多出来的字段
            for(ColumnInfo column : table.getColumns()){
                if(!existsColumnNameSet.contains(column.getName())){
                    // 添加多余字段错误信息
                    result.addError(new ExtraColumnValidationError(name, table, column));
                }
            }
        }
        return result;
    }

}
