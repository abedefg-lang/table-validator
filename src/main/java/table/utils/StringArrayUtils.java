package table.utils;

/**
 * @author lijia
 * @description 字符串数组工具类
 * @date 2021-07-28 10:54
 */
public class StringArrayUtils {


    public static boolean matches(String str, String[] regexArr){
        // 特判
        if(regexArr == null || regexArr.length == 0){
            return false;
        }
        // 循环判断是否匹配
        for(String regex : regexArr){
            if(str.matches(regex)){
                return true;
            }
        }
        return false;
    }
}
