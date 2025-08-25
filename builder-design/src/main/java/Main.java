public class Main {
    public static void main(String[] args) {

        User user = User.builder()
                .name("Tom")
                .age(19)
                .build();
        System.out.println(user.getName() + " " + user.getAge());


        String selectSQL = SQL.builder(SQL.SQLType.SELECT)
                .columns("name", "age")
                .table("user")
                .where("gender = 'Male'")
                .build();
        System.out.println(selectSQL);

        String updateSQL = SQL.builder(SQL.SQLType.UPDATE)
                .columns("name", "age")
                .table("user")
                .set("name", "张三")
                .set("age", "18")
                .where("gender = 'Male'")
                .build();
        System.out.println(updateSQL);

    }
}
