package pers.tom.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lijia
 * @description
 * @date 2021-07-28 17:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableInfos {

    /**
     * 表信息
     */
    private List<TableInfo> tableInfoList;

    /**
     * 验证当前表信息 是否包含其他表信息
     * 这里的包含的意思是 this必须要有other中的所有表 所有字段(名称 类型)
     *
     * @param other 其余的表信息
     */
    public void validationContains(TableInfos other){

    }

}
