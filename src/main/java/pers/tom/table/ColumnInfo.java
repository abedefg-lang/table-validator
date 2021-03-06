package pers.tom.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author lijia
 * @description 字段信息
 * @date 2021-07-28 11:01
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ColumnInfo {

    /**字段名*/
    private String name;

    /**字段类型*/
    private String columnType;

    /**对应的java类型*/
    private Class<?> javaType;

    /**字段备注*/
    private String comment;

    /**该字段是否是主键*/
    private boolean primary;

    @Override
    public boolean equals(Object other){
        // 当名字相同 java类型相同 primary相同
        if(other instanceof ColumnInfo){
            ColumnInfo otherColumn = (ColumnInfo) other;
            return primary && name.equals(otherColumn.name) && javaType == otherColumn.javaType;
        }
        return false;
    }

}
