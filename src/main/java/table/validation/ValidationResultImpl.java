package table.validation;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import table.validation.error.ValidationError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author tom
 * @description
 * @date 2021/7/28 23:28
 */
@Data
public class ValidationResultImpl implements ValidationResult{

    /**错误信息*/
    private List<ValidationError> errors;

    @Override
    public boolean hasError() {
        // 如果没有错误信息说明是包含的
        return !CollectionUtil.isEmpty(errors);
    }

    @Override
    public void addError(ValidationError error) {
        if(errors == null){
            errors = new ArrayList<>();
        }
        errors.add(error);
    }

    @Override
    public List<ValidationError> getErrors() {
        return errors == null ? Collections.emptyList() : Collections.unmodifiableList(errors);
    }
}
