package table.validation;


import table.validation.error.ValidationError;

import java.util.List;

/**
 * @author lijia
 * @description 验证结果
 * @date 2021/7/28 22:38
 */
public interface ValidationResult {

    /**
     * 是否有错误信息
     * @return boolean
     */
    boolean hasError();

    /**
     * 添加错误信息
     * @param error error
     */
    void addError(ValidationError error);

    /**
     * 获取错误信息
     * @return errors
     */
    List<ValidationError> getErrors();

}
