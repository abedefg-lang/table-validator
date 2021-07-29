package pers.tom.table.validation.error;


import pers.tom.table.ColumnInfo;
import pers.tom.table.TableInfo;

/**
 * @author lijia
 * @description 多余字段错误信息
 * @date 2021-07-29 12:28
 */
public class ExtraColumnValidationError extends AbstractColumnValidationError{

    /**多余字段*/
    private final ColumnInfo extraColumn;

    public ExtraColumnValidationError(String collectionName, TableInfo errorTable, ColumnInfo extraColumn) {
        super(collectionName, errorTable);
        this.extraColumn = extraColumn;
    }

    public ColumnInfo getExtraColumn() {
        return extraColumn;
    }

    @Override
    public String getMessage() {
        return String.format("多出字段错误 : 集合 [%s], 表 [%s], 多出字段 : [%s]", collectionName, extraColumn.getName(), extraColumn.getName());
    }
}
