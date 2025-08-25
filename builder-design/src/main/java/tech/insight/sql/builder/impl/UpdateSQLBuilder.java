package tech.insight.sql.builder.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Deprecated
public class UpdateSQLBuilder {
    private String table;

    private Map<String, String> setMap = new LinkedHashMap<>();

    private String where;

    public UpdateSQLBuilder() {

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
