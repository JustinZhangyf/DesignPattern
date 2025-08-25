import java.util.Arrays;
import java.util.List;

public class SQLDSL {


    public static SelectSQLBuilder select(String... columns) {
        return new SelectSQLBuilder(columns);
    }

    // DSL风格的builder, 通过接口的方式来约束方法调用的顺序
    interface FromBuilder {
        WhereBuilder table(String table);
    }

    interface WhereBuilder {
        SelectBuilder where(String where);
    }

    interface SelectBuilder {
        SelectBuilder select(String... columns);

        String build();
    }

    public static class SelectSQLBuilder implements FromBuilder, WhereBuilder, SelectBuilder {
        private String[] columns;

        private String table;

        private String where;

        private SelectSQLBuilder(String... columns) {
            this.columns = columns;
        }

        @Override
        public WhereBuilder table(String table) {
            this.table = table;
            return this;
        }

        @Override
        public SelectBuilder where(String where) {
            this.where = where;
            return this;
        }

        @Override
        public SelectBuilder select(String... columns) {
            List<String> list = Arrays.asList(this.columns);
            list.addAll(Arrays.asList(columns));
            this.columns = list.toArray(new String[list.size()]);
            return this;
        }

        @Override
        public String build() {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ").append(String.join(", ", columns))
                    .append(" FROM ").append(table);
            if (where != null) {
                sb.append(" WHERE ").append(where).append(";");
            }
            return sb.toString();

        }
    }
}
