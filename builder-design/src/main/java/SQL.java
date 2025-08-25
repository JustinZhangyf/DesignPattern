import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

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


    public static class SelectSQLBuilder {
        private String[] columns;

        private String table;

        private String where;

        private SelectSQLBuilder(String... columns) {
            this.columns = columns;
        }

        public SelectSQLBuilder from(String table) {
            this.table = table;
            return this;
        }

        public SelectSQLBuilder where(String where) {
            this.where = where;
            return this;
        }

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

    public static class UpdateSQLBuilder {
        private String table;

        private Map<String, String> setMap = new LinkedHashMap<>();

        private String where;

        private UpdateSQLBuilder() {

        }

        public UpdateSQLBuilder table(String table) {
            this.table = table;
            return this;
        }

        public UpdateSQLBuilder set(String key, String value) {
            setMap.put(key, value);
            return this;
        }

        public UpdateSQLBuilder where(String where) {
            this.where = where;
            return this;
        }

        public String build() {
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ").append(table).append(" SET ");
            String setL = setMap.entrySet().stream()
                    .map(entry -> entry.getKey() + " = " + entry.getValue())
                    .collect(Collectors.joining(", "));
            sb.append(setL);
            if (where != null) {
                sb.append(" WHERE ").append(where).append(";");
            }
            return sb.toString();
        }

    }

    public static class SQLBuilder {

        private SQLType sqlType;

        private String[] columns;

        private String table;

        private String where;

        private Map<String, String> setMap = new LinkedHashMap<>();


        private SQLBuilder() {

        }

        private SQLBuilder(SQLType sqlType) {
            this.sqlType = sqlType;
        }

        public SQLBuilder columns(String... columns) {
            this.columns = columns;
            return this;
        }

        public SQLBuilder table(String table) {
            this.table = table;
            return this;
        }

        public SQLBuilder where(String where) {
            this.where = where;
            return this;
        }

        public SQLBuilder set(String key, String value) {
            setMap.put(key, value);
            return this;
        }


        public String build() {
            StringBuilder sb = new StringBuilder();
            switch (sqlType) {
                case SELECT -> {
                    sb.append("SELECT ").append(String.join(", ", columns))
                            .append(" FROM ").append(table);
                    if (where != null) {
                        sb.append(" WHERE ").append(where).append(";");
                    }
                }
                case UPDATE -> {
                    sb.append("UPDATE ").append(table).append(" SET ");
                    String setL = setMap.entrySet().stream()
                            .map(entry -> entry.getKey() + " = " + entry.getValue())
                            .collect(Collectors.joining(", "));
                    sb.append(setL);
                    if (where != null) {
                        sb.append(" WHERE ").append(where).append(";");
                    }
                }
                default -> throw new UnsupportedOperationException("暂时不支持该类型的操作");
            }
            return sb.toString();
        }

    }

    public enum SQLType {
        SELECT,
        INSERT,
        UPDATE,
        DELETE
    }

}
