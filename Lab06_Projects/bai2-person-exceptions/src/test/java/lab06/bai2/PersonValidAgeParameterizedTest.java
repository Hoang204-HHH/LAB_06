package lab06.bai2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PersonValidAgeParameterizedTest {

    @Parameterized.Parameters(name = "age={0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0},
                {1},
                {18},
                {60}
        });
    }

    private final int age;

    public PersonValidAgeParameterizedTest(int age) {
        this.age = age;
    }

    @Test
    public void shouldCreatePersonForValidAge() {
        Person p = new Person("Test", age);
        assertEquals(age, p.getAge());
    }
}
