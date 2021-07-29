package table.validation.error;


import table.TableInfo;

/**
 * @author lijia
 * @description
 * @date 2021-07-29 12:21
 */
public abstract class AbstractColumnValidationError implements ColumnValidationError {

    protected final String collectionName;

    protected final TableInfo errorTable;

    protected AbstractColumnValidationError(String collectionName, TableInfo errorTable) {
        this.collectionName = collectionName;
        this.errorTable = errorTable;
    }

    @Override
    public String getErrorCollectionName(){
        return collectionName;
    }

    @Override
    public TableInfo getErrorTable() {
        return errorTable;
    }
}
