package tech.insight.sql.builder.impl.restructure;

import org.apache.commons.lang3.StringUtils;
import tech.insight.sql.builder.FromStage;
import tech.insight.sql.builder.SetStage;
import tech.insight.sql.builder.WhereStage;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class UpdateBuilder implements FromStage<SetStage>, SetStage, WhereStage {

    private static final UpdateBuilder INSTANCE = new UpdateBuilder();

    private String table;

    private Map<String, String> setMap = new LinkedHashMap<>();

    private String where;

    private UpdateBuilder() {
    }

    public static UpdateBuilder create() {
        INSTANCE.table = null;
        INSTANCE.where = null;
        INSTANCE.setMap.clear();
        return INSTANCE;
    }

    @Override
    public SetStage from(String table) {
        this.table = table;
        return this;
    }

    @Override
    public SetStage set(String column, String value) {
        this.setMap.put(column, value);
        return this;
    }

    @Override
    public WhereStage setFinal(String column, String value) {
        this.setMap.put(column, value);
        return this;
    }

    @Override
    public WhereStage where(String where, String operator) {
        if (StringUtils.isBlank(this.where)) {
            this.where = where;
            return this;
        }
        this.where = this.where + " " + operator + " " + where;
        return this;
    }

    @Override
    public WhereStage where(String where) {
        this.where = where;
        return this;
    }


    @Override
    public String build() {
        StringBuilder updateStr = new StringBuilder();
        updateStr.append("UPDATE ").append(table).append(" SET ");
        String setL = setMap.entrySet().stream()
                .map(entry -> entry.getKey() + " = " + entry.getValue())
                .collect(Collectors.joining(", "));
        updateStr.append(setL);
        if (where != null) {
            updateStr.append(" WHERE ").append(where).append(";");
        }
        return updateStr.toString();
    }
}
