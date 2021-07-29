package pers.tom.table.factory.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import pers.tom.table.TableInfo;

/**
 * @author tom
 * @description
 * @date 2021/7/28 23:40
 */
@Getter
@Setter
@Accessors(chain = true)
public class EntityTableInfo extends TableInfo {

    /**entity class*/
    private Class<?> entityClass;
}
