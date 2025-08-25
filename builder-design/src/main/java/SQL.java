import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SQL {

    private SQL() {

    }

    public static SQLBuilder builder(SQLType sqlType) {
        return new SQLBuilder(sqlType);
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
