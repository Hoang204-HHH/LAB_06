package lab06.bai3;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

public class ErrorCollectorExampleTest {

    /**
     * Phiên bản chạy xanh: dùng collector tùy biến để thu thập lỗi và cho phép assert lại số lỗi.
     */
    @Test
    public void customCollector_shouldCollectErrors() {
        CollectingErrorCollector collector = new CollectingErrorCollector();

        collector.addError(new Throwable("There is an error in first line"));
        collector.addError(new Throwable("There is an error in second line"));

        try {
            // lỗi chủ động
            org.junit.Assert.assertTrue("A should equal B", "A".equals("B"));
        } catch (Throwable t) {
            collector.addError(t);
        }

        assertEquals(3, collector.size());
        // checkThat demo (không fail ngay)
        collector.checkThat("1+1 should be 2", 1 + 1, is(2));
    }
}
