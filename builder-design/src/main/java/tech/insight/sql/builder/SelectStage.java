package tech.insight.sql.builder;

// 该类用于描述SELECT语句的开始
public interface SelectStage extends SQLStage {

    FromStage<WhereStage> select();

    FromStage<WhereStage> multiSelect(String... column);
}
