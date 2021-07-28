package pers.tom.table;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.Test;
import pers.tom.table.factory.entity.EntityTableInfosFactory;
import pers.tom.table.factory.entity.parser.EntityTableInfoParser;
import pers.tom.table.factory.jdbc.JdbcTableInfosFactory;


/**
 * @author lijia
 * @description
 * @date 2021-07-28 14:59
 */
public class TableTest {

    @Test
    public void test001(){

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://172.16.3.9:3306?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai");
        dataSource.setUser("root");
        dataSource.setPassword("ser-dev-root");

        JdbcTableInfosFactory factory = new JdbcTableInfosFactory(dataSource)
                .setDatabaseName("test_property_510000_2_0001");

//        factory.get()

    }

    @Test
    public void test002(){
        EntityTableInfosFactory factory = new EntityTableInfosFactory(new EntityTableInfoParser() {
            @Override
            public TableInfo parse(Class<?> entityClass) {
                return new TableInfo(entityClass.getSimpleName(), entityClass.getSimpleName());
            }
        });

    }
}
