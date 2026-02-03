package lab06.bai6;

public interface UserRepository {
    boolean existsByUsername(String username);
    User findByUsername(String username);
    User save(User user);   // create or update
    void delete(String username);
}
