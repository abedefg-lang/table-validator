import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.Test;
import pers.tom.table.TableInfoCollection;
import pers.tom.table.factory.jdbc.JdbcTableInfosFactory;
import pers.tom.table.factory.jdbc.TableNameFilter;
import pers.tom.table.validation.ValidationResult;
import pers.tom.table.validation.error.ValidationError;


/**
 * @author lijia
 * @description
 * @date 2021-07-28 14:59
 */
public class TableTest {

    @Test
    public void test001(){

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai");
        dataSource.setUser("root");
        dataSource.setPassword("root");

        JdbcTableInfosFactory factory = new JdbcTableInfosFactory(dataSource);

        TableInfoCollection c1 = new TableInfoCollection("c1", factory.get("dev_ucenter", TableNameFilter.ofIncludeRegex("room_bind_apply_property")));
        TableInfoCollection c2 = new TableInfoCollection("c2", factory.get("dev_ucenter_data", TableNameFilter.ofIncludeRegex("room_bind_apply_property")));

        // 判断c1是否包含c2
        ValidationResult result = c2.validationContains(c1);
        for(ValidationError error : result.getErrors()){
            System.out.println(error.getMessage());
        }
    }

}
