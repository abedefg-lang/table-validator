package pers.tom.table.converter;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lijia
 * @description 数据库类型转换器
 * @date 2021-07-28 11:46
 */
public class DbTypeConverter implements JavaTypeConverter {

    /**缓存类型映射*/
    private Map<String, Class<?>> typeMappings;


    public DbTypeConverter(){
        this.init();
    }

    /**
     * 注册映射关系
     * @param otherType otherType
     * @param type type
     */
    public void registerMapping(String otherType, Class<?> type){
        if (typeMappings == null) {
            typeMappings = new HashMap<>();
        }
        typeMappings.put(otherType, type);
    }


    @Override
    public Class<?> convert(String dbType) {

        //判断是否有() 如果有需要截取
        int index = dbType.indexOf("(");
        String realDbType = index > 0 ? dbType.substring(0, index) : dbType;
        return typeMappings.get(realDbType);
    }

    /**
     * 初始化类型映射
     */
    private void init() {
        registerMapping("int", int.class);
        registerMapping("varchar", String.class);
        registerMapping("date", Date.class);
        registerMapping("datetime", Date.class);
        registerMapping("timestamp", Timestamp.class);
        registerMapping("char", String.class);
        registerMapping("text", String.class);
        registerMapping("longtext", String.class);
        registerMapping("mediumtext", String.class);
        registerMapping("longvarchar", String.class);
        registerMapping("numeric", BigDecimal.class);
        registerMapping("decimal", BigDecimal.class);
        registerMapping("bit", boolean.class);
        registerMapping("tinyint", byte.class);
        registerMapping("smallint", short.class);
        registerMapping("integer", int.class);
        registerMapping("bigint", long.class);
        registerMapping("real", float.class);
        registerMapping("float", float.class);
        registerMapping("double", double.class);
        registerMapping("binary", byte[].class);
        registerMapping("time", Time.class);
    }
}
