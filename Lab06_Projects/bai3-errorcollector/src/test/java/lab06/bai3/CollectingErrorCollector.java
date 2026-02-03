package lab06.bai3;

import org.junit.rules.ErrorCollector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ErrorCollector bản tùy biến để: thu thập lỗi nhưng KHÔNG làm test fail tự động,
 * giúp bạn assert số lỗi trong unit test.
 */
public class CollectingErrorCollector extends ErrorCollector {
    private final List<Throwable> errors = new ArrayList<>();

    @Override
    public void addError(Throwable error) {
        // Không gọi super.addError(error) để tránh fail tự động
        errors.add(error);
    }

    public int size() {
        return errors.size();
    }

    public List<Throwable> getErrors() {
        return Collections.unmodifiableList(errors);
    }
}
