package tech.insight.sql.builder.impl.restructure;

import org.apache.commons.lang3.StringUtils;
import tech.insight.sql.builder.FromStage;
import tech.insight.sql.builder.WhereStage;

public class DeleteBuilder implements FromStage<WhereStage>, WhereStage {
    private static final DeleteBuilder INSTANCE = new DeleteBuilder();

    private String table;

    private String where;

    private DeleteBuilder() {
    }
    public static DeleteBuilder create() {
        INSTANCE.table = null;
        INSTANCE.where = null;
        return INSTANCE;
    }
    @Override
    public WhereStage from(String table) {
        this.table = table;
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
        StringBuilder sqlStr = new StringBuilder();

        sqlStr.append("DELETE FROM ").append(table)
                .append(" WHERE ").append(where)
                .append(";");
        return sqlStr.toString();
    }


}
