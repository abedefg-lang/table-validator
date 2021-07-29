package pers.tom.table.validation.error;


import pers.tom.table.ColumnInfo;
import pers.tom.table.TableInfo;

/**
 * @author lijia
 * @description 不匹配字段错误信息
 * @date 2021-07-29 14:06
 */
public class MismatchColumnValidationError extends AbstractColumnValidationError{

    /**比较的当前字段*/
    private final ColumnInfo currentColumn;

    /**比较的目标字段*/
    private final ColumnInfo targetColumn;

    public MismatchColumnValidationError(String collectionName, TableInfo errorTable, ColumnInfo currentColumn, ColumnInfo targetColumn) {
        super(collectionName, errorTable);
        this.currentColumn = currentColumn;
        this.targetColumn = targetColumn;
    }

    public ColumnInfo getCurrentColumn() {
        return currentColumn;
    }

    public ColumnInfo getTargetColumn() {
        return targetColumn;
    }

    @Override
    public String getMessage() {
        return String.format("字段匹配错误 : 集合 [%s], 表 [%s], 不匹配字段 [%s]", collectionName, errorTable.getName(), currentColumn.getName());
    }
}
