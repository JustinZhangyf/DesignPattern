public class Main {
    public static void main(String[] args) {

        User user = User.builder()
                .name("Tom")
                .age(19)
                .build();
        System.out.println(user.getName() + " " + user.getAge());
    }
}
