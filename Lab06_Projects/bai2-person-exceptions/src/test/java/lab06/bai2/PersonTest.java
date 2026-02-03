package lab06.bai2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PersonTest {

    // 1) ExpectedException Rule
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testExpectedExceptionRule_ageNegative_shouldThrow() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Invalid age");
        new Person("Fpoly", -1);
    }

    // 2) @Test(expected=...)
    @Test(expected = IllegalArgumentException.class)
    public void testAnnotationExpected_ageNegative_shouldThrow() {
        new Person("Fpoly", -1);
    }

    // 3) try-catch
    @Test
    public void testTryCatch_ageNegative_shouldThrow() {
        try {
            new Person("Fpoly", -1);
            fail("Should have thrown IllegalArgumentException because age is invalid!");
        } catch (IllegalArgumentException ex) {
            // ok
        }
    }

    @Test
    public void testValidAge_ageZero_shouldCreatePerson() {
        Person p = new Person("A", 0);
        assertEquals(0, p.getAge());
    }
}
