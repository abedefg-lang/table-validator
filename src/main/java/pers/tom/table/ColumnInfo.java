package pers.tom.table;

import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

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

    /**转换之后的属性名*/
    private String fieldName;

    /**字段类型*/
    private String columnType;

    /**字段类型转化成java类型*/
    private Class<?> javaType;

    /**字段备注*/
    private String comment;

    /**字段的键*/
    private String columnKey;

    /**该字段是否是主键*/
    private boolean primary;


    public static ColumnInfo findPrimary(List<ColumnInfo> columnInfos){
        // 特判
        if(CollectionUtil.isEmpty(columnInfos)){
            return null;
        }
        for(ColumnInfo info : columnInfos){
            if(info.isPrimary()){
                return info;
            }
        }
        return null;
    }

}
