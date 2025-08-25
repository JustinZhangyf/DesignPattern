package tech.insight.sql.builder.impl.restructure;

import tech.insight.sql.builder.AssembleStage;
import tech.insight.sql.builder.FromStage;

import java.util.LinkedHashMap;
import java.util.Map;

public class InsertBuilder implements FromStage<AssembleStage>, AssembleStage {

    private String table;

    private Map<String, String> setMap = new LinkedHashMap<>();

    @Override
    public AssembleStage from(String table) {
        this.table = table;
        return this;
    }

    @Override
    public AssembleStage assemble(String column, String value) {
        this.setMap.put(column, value);
        return this;
    }

    @Override
    public String build() {
        StringBuilder sqlStr = new StringBuilder();


        // LinkedHashMap中的keySet和values值仍然是一一对应的关系，可以直接用，或者保险起见用entrySet获得两个一一对应的list再处理
        sqlStr.append("INSERT INTO ").append(table)
                .append(" (")
                .append(String.join(", ", setMap.keySet()))
                .append(") VALUES (")
                .append(String.join(", ", setMap.values()))
                .append(");");

        return sqlStr.toString();
    }


}
