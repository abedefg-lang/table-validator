package table.validation.error;


import table.TableInfo;

/**
 * @author lijia
 * @description 字段验证错误信息
 * @date 2021-07-29 12:17
 */
public interface ColumnValidationError extends TableValidationError{

    /**
     * 获取出错的表信息
     * @return table info
     */
    TableInfo getErrorTable();
}
