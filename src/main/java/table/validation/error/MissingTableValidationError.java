package table.validation.error;


import table.TableInfo;

/**
 * @author lijia
 * @description 缺少表错误信息
 * @date 2021-07-29 12:18
 */
public class MissingTableValidationError implements TableValidationError {

    private final String collectionName;

    private final TableInfo missingTable;

    public MissingTableValidationError(String collectionName, TableInfo missingTable) {
        this.collectionName = collectionName;
        this.missingTable = missingTable;
    }

    public TableInfo getMissingTable() {
        return missingTable;
    }

    @Override
    public String getErrorCollectionName() {
        return collectionName;
    }

    @Override
    public String getMessage() {
        return String.format("缺少表错误 : 集合 [%s], 缺少表 : [%s]", collectionName, missingTable.getName());
    }
}
