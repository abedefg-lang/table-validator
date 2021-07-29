package table.utils;

/**
 * @author lijia
 * @description 与名字相关的工具类
 * @date 2021-01-04 10:49
 */
public class NamingUtils {

    /**
     * 首字母大写
     */
    public static String initialUppercase(String str){
        //判断如果字符串为空  或者为空串  或者首字母本身就是大写  返回原字符串
        if(str == null || "".equals(str) || isUppercase(str.charAt(0))) return str;
        char[] chars = str.toCharArray();
        chars[0] = (char)(chars[0] - 32);
        return new String(chars);
    }


    /**
     * 首字母小写
     */
    public static String initialLowercase(String str){
        //判断如果字符串为空  或者为空串  或者首字母本身就是小写写  返回原字符串
        if(str == null || "".equals(str) || isLowerCase(str.charAt(0))) return str;
        char[] chars = str.toCharArray();
        chars[0] = (char)(chars[0] + 32);
        return new String(chars);
    }

    /**
     * 将其他命名规则转化为驼峰式  比如下划线  中划线  空格等于连接的方式
     */
    public static String toCamelCase(String str, String regex){
        String[] strings = str.split(regex);
        StringBuilder builder = new StringBuilder(strings[0]);
        for(int i = 1 ; i < strings.length ; i ++){
            builder.append(initialUppercase(strings[i]));
        }
        return new String(builder);
    }


    /**
     * 转换成大驼峰式
     */
    public static String toGreatCamelCase(String str, String regex){
        String className = toCamelCase(str, regex);
        return initialUppercase(className);
    }


    /**
     * 获取一个类全名的简单名字  去掉前面的包名
     */
    public static String getSimpleName(String className){
        int index = className.lastIndexOf(".");
        return (index > 0) ? className.substring(index+1) : className;
    }


    /**
     * 判断是否是大写字母
     */
    public static boolean isUppercase(char word){
        return 'A' <= word && 'Z' >= word;
    }

    /**
     * 判断是否是小写字母
     */
    public static boolean  isLowerCase(char word){
        return 'a' <= word && 'z' >= word;
    }

    /**
     * 判断一个字符是否是字母
     */
    public static boolean isLetter(char word){
        return isUppercase(word) || isLowerCase(word);
    }
}
