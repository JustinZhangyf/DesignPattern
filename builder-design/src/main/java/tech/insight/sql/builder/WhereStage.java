package tech.insight.sql.builder;

public interface WhereStage extends BuildStage {

    WhereStage where(String where, String operator);

    WhereStage where(String where);
}
