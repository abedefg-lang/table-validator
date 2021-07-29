package pers.tom.table.validation.error;


import pers.tom.table.ColumnInfo;
import pers.tom.table.TableInfo;

/**
 * @author lijia
 * @description 缺少字段错误信息
 * @date 2021-07-29 12:23
 */
public class MissingColumnValidationError extends AbstractColumnValidationError{

    /**缺少的字段信息*/
    private final ColumnInfo missingColumn;


    public MissingColumnValidationError(String collectionName, TableInfo errorTable, ColumnInfo missingColumn) {
        super(collectionName, errorTable);
        this.missingColumn = missingColumn;
    }


    public ColumnInfo getMissingColumn() {
        return missingColumn;
    }

    @Override
    public String getMessage() {
        return String.format("缺少字段错误 : 集合 [%s], 表 [%s], 缺少字段 : [%s]", collectionName, errorTable.getName(), missingColumn.getName());
    }
}
