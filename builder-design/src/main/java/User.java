public class User {

    private final String name;
    private final int age;

    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
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

        public User build() {
            check();
            return new User(this);
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

    }
}
