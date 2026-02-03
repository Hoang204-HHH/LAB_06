package lab06.bai5;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class OrganizationUnitServiceTest {

    @Test(expected = IllegalArgumentException.class)
    public void create_missingName_shouldThrow() {
        new OrganizationUnitService(new InMemoryOrganizationUnitRepository())
                .create("OU01", "   ", "desc");
    }

    @Test(expected = IllegalStateException.class)
    public void create_duplicateUnitId_shouldThrow() {
        InMemoryOrganizationUnitRepository repo = new InMemoryOrganizationUnitRepository();
        OrganizationUnitService s = new OrganizationUnitService(repo);
        s.create("OU01", "HR", "");
        s.create("OU01", "HR2", "");
    }

    @Test(expected = RuntimeException.class)
    public void create_dbError_shouldThrowRuntime() {
        new OrganizationUnitService(new FailingOrganizationUnitRepository())
                .create("OU01", "HR", "");
    }

    @Test
    public void create_happyPath_shouldSave() {
        OrganizationUnitService s = new OrganizationUnitService(new InMemoryOrganizationUnitRepository());
        OrganizationUnit u = s.create("OU01", "HR", "");
        assertEquals("OU01", u.getUnitId());
        assertEquals("HR", u.getName());
        assertNull(u.getDescription());
    }

    @RunWith(Parameterized.class)
    public static class InvalidDescriptionLengthParameterizedTest {
        @Parameterized.Parameters(name = "len={0}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {401},
                    {500}
            });
        }

        private final int len;

        public InvalidDescriptionLengthParameterizedTest(int len) {
            this.len = len;
        }

        @Test(expected = IllegalArgumentException.class)
        public void create_descriptionTooLong_shouldThrow() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) sb.append('a');
            new OrganizationUnitService(new InMemoryOrganizationUnitRepository())
                    .create("OUX", "Name", sb.toString());
        }
    }
}
