package lab06.bai3;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Chạy test bằng JUnitCore và in kết quả ra console (giống lab).
 * Bạn có thể đổi class test bên dưới để chạy demo FAIL.
 */
public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ErrorCollectorExampleTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println("Result=" + result.wasSuccessful());
    }
}
