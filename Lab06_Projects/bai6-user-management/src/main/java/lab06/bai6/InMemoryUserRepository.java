package lab06.bai6;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> store = new HashMap<>();

    @Override
    public boolean existsByUsername(String username) {
        return store.containsKey(username);
    }

    @Override
    public User findByUsername(String username) {
        return store.get(username);
    }

    @Override
    public User save(User user) {
        store.put(user.getUsername(), user);
        return user;
    }

    @Override
    public void delete(String username) {
        store.remove(username);
    }
}
