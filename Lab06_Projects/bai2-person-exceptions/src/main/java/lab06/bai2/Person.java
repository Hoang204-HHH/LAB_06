package lab06.bai2;

public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        // Theo mô tả Lab: age < 0 là không hợp lệ
        if (age < 0) {
            throw new IllegalArgumentException("Invalid age: " + age);
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
