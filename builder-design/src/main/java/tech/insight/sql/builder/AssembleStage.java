package tech.insight.sql.builder;

// AssembleStage 该阶段用于insert sql 组装 column和value的值
public interface AssembleStage extends BuildStage{

    AssembleStage assemble(String column, String value);

}
