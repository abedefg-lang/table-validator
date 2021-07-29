package pers.tom.table.validation.error;

/**
 * @author tom
 * @description 验证错误信息
 * @date 2021/7/28 23:11
 */
public interface ValidationError {

    /**
     * 出现错误对的集合名称
     * @return collection name
     */
    String getErrorCollectionName();

    /**
     * 获取错误信息
     * @return message
     */
    String getMessage();
}
