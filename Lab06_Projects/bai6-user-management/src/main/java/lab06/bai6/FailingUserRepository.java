package lab06.bai6;

public class FailingUserRepository implements UserRepository {
    @Override
    public boolean existsByUsername(String username) {
        throw new RuntimeException("DB connection error");
    }

    @Override
    public User findByUsername(String username) {
        throw new RuntimeException("DB connection error");
    }

    @Override
    public User save(User user) {
        throw new RuntimeException("DB connection error");
    }

    @Override
    public void delete(String username) {
        throw new RuntimeException("DB connection error");
    }
}
