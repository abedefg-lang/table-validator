package pers.tom.table.factory.jdbc.dialect;

/**
 * @author lijia
 * @description
 * @date 2021-07-28 11:54
 */
public enum Dialects {

    MYSQL("mysql", new MysqlDialect());

    private final String name;

    private final Dialect dialect;

    Dialects(String name, Dialect dialect) {
        this.name = name;
        this.dialect = dialect;
    }

    public String getName() {
        return name;
    }

    public Dialect getDialect() {
        return dialect;
    }
}
