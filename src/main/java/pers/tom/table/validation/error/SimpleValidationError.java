package pers.tom.table.validation.error;

/**
 * @author lijia
 * @description 简单错误信息
 * @date 2021-07-29 11:30
 */
public class SimpleValidationError implements ValidationError {

    private final String collectionName;

    /**错误信息*/
    private final String message;

    public SimpleValidationError(String collectionName, String message) {
        this.collectionName = collectionName;
        this.message = message;
    }

    @Override
    public String getErrorCollectionName() {
        return collectionName;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
