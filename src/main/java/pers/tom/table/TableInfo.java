package pers.tom.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author lijia
 * @description
 * @date 2021-07-28 11:00
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TableInfo {

    /**对应表名*/
    private String name;

    /**类名*/
    private String className;

    /**备注*/
    private String comment;

    /**该表的所有字段信息*/
    private List<ColumnInfo> columns;

    /**主键字段*/
    private ColumnInfo primary;


    public TableInfo(String name, String className){
        this.name = name;
        this.className = className;
    }

    public TableInfo setColumns(List<ColumnInfo> columns){
        this.columns = columns;
        // 重新设置主键
        this.primary = ColumnInfo.findPrimary(columns);
        return this;
    }

}
