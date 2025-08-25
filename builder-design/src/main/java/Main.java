import tech.insight.sql.SQLQuery;
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


        System.out.println();
        System.out.println("==========dsl 优化==========");

        String select = SQL.select("name", "age").from("user").where("gender = 'Male'").build();
        System.out.println(select);

        String update = SQL.update().table("user").set("name", "张三").set("age", "18").where("gender = 'Male'").build();
        System.out.println(update);

        System.out.println();
        System.out.println("==========dsl 优化2==========");
        System.out.println();
        System.out.println("==========Insert==========");
        System.out.println(SQLQuery
                .insert()
                .from("user")
                .assemble("name", "张三")
                .assemble("age", "18")
                .build()
        );

        System.out.println();
        System.out.println("==========Select ALL==========");
        System.out.println(SQLQuery
                .select()
                .from("user")
                .where("age > 18")
                .where("gender = 'Male'", "and")
                .build()
        );
        System.out.println();
        System.out.println("==========Multiple Select==========");
        System.out.println(SQLQuery
                .multiSelect("name", "age")
                .from("user")
                .where("gender = 'Male'")
                .build()
        );
        System.out.println();
        System.out.println("==========Update==========");
        System.out.println(SQLQuery
                .update()
                .from("user")
                .set("name", "张三")
                .setFinal("age", "18")
                .where("gender = 'Male'")
                .build()
        );
        System.out.println();
        System.out.println("==========Delete==========");
        System.out.println(SQLQuery
                .delete()
                .from("user")
                .where("gender = 'Male'")
                .build()
        );

    }
}
