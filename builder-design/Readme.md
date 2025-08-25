使用建造者模式，依据DSL实现创建Insert SQL, Update SQL, Delete SQL, Select SQL

选择了select就只能使用select语句的一些方法，比如select,
选择了update sql 就只能使用update语句的一些方法，比如set...

因此需要定义创建SQL语句的流程，需要将四种DML语句的流程共同点提取出来

1. Insert SQL
    1. insert into table (columns) values (values)
2. Update SQL
    1. update table set columns = values where condition
3. Delete SQL
    1. delete from table where condition
4. Select SQL
    1. select columns from table where condition

四种DML语句 process
为了使流程统一，需要将各个语句的独立逻辑放在第一步，
比如insert语句的columns和values
所以创建SQL语句的流程如下：
第一阶段：
insert-> columns value 赋值
update-> set columns value
select-> columns
delete->
第二阶段：
确认表名
第三阶段：
确认条件
第四阶段：
当且仅当确认条件之后，才可使用build方法创建SQL语句，防止创建SQL语句时，条件为空 影响整表

