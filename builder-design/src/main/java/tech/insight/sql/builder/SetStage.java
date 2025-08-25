package tech.insight.sql.builder;

// SetStage 该阶段用于设置要更新的字段和值
public interface SetStage extends SQLStage{

    SetStage set(String column, String value);

    WhereStage setFinal(String column, String value);

}
