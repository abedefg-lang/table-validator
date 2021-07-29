package table.converter;

/**
 * @author lijia
 * @description java类型转换器 将其他类型转换class
 * @date 2021-07-28 11:10
 */
public interface JavaTypeConverter {

    /**
     * 转换逻辑
     * @param otherType 其他类型
     * @return class
     */
    Class<?> convert(String otherType);
}
