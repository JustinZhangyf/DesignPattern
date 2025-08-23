public class User {

    private String name;
    private int age;

    private User() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


    public static class Builder {
        private String name;
        private int age;

        private Builder() {
        }


        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        private void check() {
            if (name == null) {
                throw new IllegalArgumentException("name is null");
            }

            if (age <= 0) {
                throw new IllegalArgumentException("age <= 0");
            }

            if (age > 120) {
                throw new IllegalArgumentException("age > 120");
            }

            if (age < 18 && name.startsWith("Tom")) {
                throw new IllegalArgumentException("");
            }
        }

        public User build() {
            User user = new User();
            user.name = this.name;
            user.age = this.age;

            check();
            return user;
        }

    }
}
