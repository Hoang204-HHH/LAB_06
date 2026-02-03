package lab06.bai6;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test(expected = IllegalArgumentException.class)
    public void create_missingUsername_shouldThrow() {
        new UserService(new InMemoryUserRepository())
                .create(" ", "12345678", "A", "a@b.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_invalidEmail_shouldThrow() {
        new UserService(new InMemoryUserRepository())
                .create("u1", "12345678", "A", "abc");
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_shortPassword_shouldThrow() {
        new UserService(new InMemoryUserRepository())
                .create("u1", "123", "A", "a@b.com");
    }

    @Test
    public void create_happyPath_shouldSave() {
        InMemoryUserRepository repo = new InMemoryUserRepository();
        UserService s = new UserService(repo);
        User u = s.create("u1", "12345678", "User 1", "u1@test.com");
        assertEquals("u1", u.getUsername());
        assertNotNull(repo.findByUsername("u1"));
    }

    @Test(expected = IllegalStateException.class)
    public void update_userNotFound_shouldThrow() {
        new UserService(new InMemoryUserRepository())
                .update("u404", "12345678", "A", "a@b.com");
    }

    @Test
    public void delete_existingUser_shouldRemove() {
        InMemoryUserRepository repo = new InMemoryUserRepository();
        UserService s = new UserService(repo);
        s.create("u1", "12345678", "User 1", "u1@test.com");
        s.delete("u1");
        assertNull(repo.findByUsername("u1"));
    }

    @Test(expected = RuntimeException.class)
    public void create_dbError_shouldThrowRuntime() {
        new UserService(new FailingUserRepository())
                .create("u1", "12345678", "User 1", "u1@test.com");
    }

    @RunWith(Parameterized.class)
    public static class EmailValidationParameterizedTest {
        @Parameterized.Parameters(name = "email={0} valid={1}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"a@b.com", true},
                    {"name.surname@domain.co", true},
                    {"abc", false},
                    {"a@b", false},
                    {"@domain.com", false}
            });
        }

        private final String email;
        private final boolean valid;

        public EmailValidationParameterizedTest(String email, boolean valid) {
            this.email = email;
            this.valid = valid;
        }

        @Test
        public void validateEmail_shouldMatchExpectation() {
            UserService s = new UserService(new InMemoryUserRepository());
            try {
                s.create("uX", "12345678", "User X", email);
                assertTrue(valid);
            } catch (IllegalArgumentException ex) {
                assertFalse(valid);
            }
        }
    }
}
