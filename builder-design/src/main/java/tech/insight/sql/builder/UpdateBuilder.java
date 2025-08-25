package tech.insight.sql.builder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class UpdateBuilder implements SetStage, FromStage, WhereStage {
    private String table;

    private Map<String, String> setMap = new LinkedHashMap<>();

    private String where;

    @Override
    public WhereStage from(String table) {
        this.table = table;
        return this;
    }

    @Override
    public SetStage set(String column, String value) {
        this.setMap.put(column, value);
        return this;
    }

    @Override
    public SetStage where(String where) {
        this.where = where;
        return this;
    }

    @Override
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
