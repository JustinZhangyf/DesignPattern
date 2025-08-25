package tech.insight.sql.builder;

// DSL风格的Stage, 通过接口的方式来约束方法调用的顺序
public interface FromStage {

    WhereStage from(String table);
}
