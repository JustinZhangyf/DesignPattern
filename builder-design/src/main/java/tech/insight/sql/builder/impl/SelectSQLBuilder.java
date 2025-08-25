package tech.insight.sql.builder.impl;

@Deprecated
public class SelectSQLBuilder {

    private String[] columns;

    private String table;

    private String where;

    public SelectSQLBuilder(String... columns) {
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
