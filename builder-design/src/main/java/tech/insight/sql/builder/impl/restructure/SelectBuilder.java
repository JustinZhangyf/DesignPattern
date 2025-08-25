package tech.insight.sql.builder.impl.restructure;

import org.apache.commons.lang3.StringUtils;
import tech.insight.sql.builder.FromStage;
import tech.insight.sql.builder.SelectStage;
import tech.insight.sql.builder.WhereStage;

public class SelectBuilder implements FromStage<WhereStage>, SelectStage, WhereStage {


    String[] columns;

    String table;

    String where;

    public SelectBuilder() {
        select();
    }

    public SelectBuilder(String... columns) {
        multiSelect(columns);
    }



    @Override
    public WhereStage from(String table) {
        this.table = table;
        return this;
    }

    @Override
    public FromStage<WhereStage> select() {
        this.columns = new String[]{"*"};
        return this;
    }

    @Override
    public FromStage<WhereStage> multiSelect(String... column) {
        this.columns = column;
        return this;
    }

    @Override
    public WhereStage where(String where, String operator) {
        if (StringUtils.isBlank(this.where)) {
            // 如果没有已有条件，直接设置
            this.where = where;
        } else {
            // 有已有条件，拼接 operator 和新条件
            this.where = this.where + " " + operator + " " + where;
        }
        return this;
    }

    @Override
    public WhereStage where(String where) {
        // 只设置第一个条件，不拼接
        this.where = where;
        return this;
    }

    @Override
    public String build() {
        StringBuilder selectStr = new StringBuilder();

        selectStr.append("SELECT ").append(String.join(", ", columns))
                .append(" FROM ").append(table);
        if (where != null) {
            selectStr.append(" WHERE ").append(where);
        }
        selectStr.append(";");

        return selectStr.toString();
    }
}
