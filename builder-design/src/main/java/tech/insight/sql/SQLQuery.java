package tech.insight.sql;

import tech.insight.sql.builder.AssembleStage;
import tech.insight.sql.builder.FromStage;
import tech.insight.sql.builder.SetStage;
import tech.insight.sql.builder.WhereStage;
import tech.insight.sql.builder.impl.restructure.DeleteBuilder;
import tech.insight.sql.builder.impl.restructure.InsertBuilder;
import tech.insight.sql.builder.impl.restructure.SelectBuilder;
import tech.insight.sql.builder.impl.restructure.UpdateBuilder;

public class SQLQuery {

    private SQLQuery() {
    }

    public static FromStage<WhereStage> select() {
        return new SelectBuilder();
    }

    public static FromStage<WhereStage> multiSelect(String... columns) {
        return new SelectBuilder(columns);
    }

    public static FromStage<SetStage> update() {
        return new UpdateBuilder();
    }

    public static FromStage<AssembleStage> insert() {
        return new InsertBuilder();
    }

    public static FromStage<WhereStage> delete() {
        return new DeleteBuilder();
    }
}
