package lab06.bai4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class JobTitleServiceTest {

    @Test(expected = IllegalArgumentException.class)
    public void create_emptyJobTitle_shouldThrow() {
        new JobTitleService(new InMemoryJobTitleRepository())
                .create("  ", "", null, "");
    }

    @Test
    public void create_jobTitleLen100_shouldPass() {
        String title = repeat('a', 100);
        JobTitle jt = new JobTitleService(new InMemoryJobTitleRepository())
                .create(title, "", 0, "");
        assertEquals(title, jt.getJobTitle());
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_jobTitleLen101_shouldThrow() {
        String title = repeat('a', 101);
        new JobTitleService(new InMemoryJobTitleRepository())
                .create(title, "", null, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_descriptionLen401_shouldThrow() {
        String desc = repeat('d', 401);
        new JobTitleService(new InMemoryJobTitleRepository())
                .create("Dev", desc, null, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_noteLen401_shouldThrow() {
        String note = repeat('n', 401);
        new JobTitleService(new InMemoryJobTitleRepository())
                .create("Dev", "", null, note);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_fileTooLarge_shouldThrow() {
        new JobTitleService(new InMemoryJobTitleRepository())
                .create("Dev", "", 1025, "");
    }

    @Test(expected = IllegalStateException.class)
    public void create_duplicateTitle_shouldThrow() {
        InMemoryJobTitleRepository repo = new InMemoryJobTitleRepository();
        JobTitleService s = new JobTitleService(repo);
        s.create("Dev", "", null, "");
        s.create("Dev", "", null, "");
    }

    @Test(expected = RuntimeException.class)
    public void create_dbError_shouldThrowRuntime() {
        new JobTitleService(new FailingJobTitleRepository())
                .create("Dev", "", null, "");
    }

    @RunWith(Parameterized.class)
    public static class ValidFileSizesParameterizedTest {
        @Parameterized.Parameters(name = "sizeKb={0}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {0}, {1}, {1024}
            });
        }

        private final int size;

        public ValidFileSizesParameterizedTest(int size) {
            this.size = size;
        }

        @Test
        public void create_validFileSizes_shouldPass() {
            new JobTitleService(new InMemoryJobTitleRepository())
                    .create("Dev" + size, "", size, "");
        }
    }

    private static String repeat(char c, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(c);
        return sb.toString();
    }
}
