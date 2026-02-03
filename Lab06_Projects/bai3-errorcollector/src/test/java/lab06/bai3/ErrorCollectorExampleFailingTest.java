package lab06.bai3;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static org.junit.Assert.assertTrue;

/**
 * Demo sát với lab: thu thập nhiều lỗi và cuối cùng FAIL.
 * Bỏ @Ignore để chạy.
 */
@Ignore("Bỏ Ignore để chạy demo giống lab (test sẽ FAIL và hiển thị failures)")
public class ErrorCollectorExampleFailingTest {

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void example() {
        collector.addError(new Throwable("There is an error in first line"));
        collector.addError(new Throwable("There is an error in second line"));

        System.out.println("Hello");
        try {
            assertTrue("A == B", "A".equals("B"));
        } catch (Throwable t) {
            collector.addError(t);
        }
        System.out.println("World!!!");
    }
}
