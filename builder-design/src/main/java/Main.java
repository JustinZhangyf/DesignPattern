import tech.insight.sql.enums.SQLType;

public class Main {
    public static void main(String[] args) {

        User user = User.builder()
                .name("Tom")
                .age(19)
                .build();
        System.out.println(user.getName() + " " + user.getAge());


        String selectSQL = SQL.builder(SQLType.SELECT)
                .columns("name", "age")
                .table("user")
                .where("gender = 'Male'")
                .build();
        System.out.println(selectSQL);

        String updateSQL = SQL.builder(SQLType.UPDATE)
                .columns("name", "age")
                .table("user")
                .set("name", "张三")
                .set("age", "18")
                .where("gender = 'Male'")
                .build();
        System.out.println(updateSQL);


        System.out.println("==========dsl 优化==========");

        String select = SQL.select("name", "age").from("user").where("gender = 'Male'").build();
        System.out.println(select);

        String update = SQL.update().table("user").set("name", "张三").set("age", "18").where("gender = 'Male'").build();
        System.out.println(update);


        System.out.println("==========dsl 优化2==========");

        String build = SQL.updateBuilder()
                .from("user")
                .where("gender = 'Male'")
                .set("name", "张三")
                .set("age", "18")
                .build();

        System.out.println(build);

    }
}
