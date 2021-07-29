package table.factory.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import table.ColumnInfo;

import java.lang.reflect.Field;

/**
 * @author tom
 * @description
 * @date 2021/7/28 23:40
 */
@Getter
@Setter
@Accessors(chain = true)
public class EntityColumnInfo extends ColumnInfo {

    /**field*/
    private Field field;

}
