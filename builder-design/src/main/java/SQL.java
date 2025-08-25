import tech.insight.sql.builder.SQLBuilder;
import tech.insight.sql.builder.SelectSQLBuilder;
import tech.insight.sql.builder.UpdateSQLBuilder;
import tech.insight.sql.enums.SQLType;

public class SQL {

    private SQL() {

    }

    // 拆分builder成独立的几个不同的builder,以免select和update不同的方法可以互相调用，
    // 比如在select中调用set方法，在update中调用select方法
    public static SelectSQLBuilder select(String... columns) {
        return new SelectSQLBuilder(columns);
    }

    public static UpdateSQLBuilder update() {
        return new UpdateSQLBuilder();
    }


    public static SQLBuilder builder(SQLType sqlType) {
        return new SQLBuilder(sqlType);
    }


}
