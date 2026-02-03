package lab06.bai6;

import java.util.regex.Pattern;

public class UserService {
    private final UserRepository repo;

    // Regex email đơn giản (đủ dùng cho bài lab)
    private static final Pattern EMAIL =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$");

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User create(String username, String password, String fullName, String email) {
        User user = validate(username, password, fullName, email);

        if (repo.existsByUsername(user.getUsername())) {
            throw new IllegalStateException("duplicate username");
        }
        return repo.save(user);
    }

    public User update(String username, String password, String fullName, String email) {
        User user = validate(username, password, fullName, email);

        if (!repo.existsByUsername(user.getUsername())) {
            throw new IllegalStateException("user not found");
        }
        return repo.save(user);
    }

    public void delete(String username) {
        username = username == null ? "" : username.trim();
        if (username.isEmpty()) throw new IllegalArgumentException("username is required");
        if (!repo.existsByUsername(username)) throw new IllegalStateException("user not found");
        repo.delete(username);
    }

    private User validate(String username, String password, String fullName, String email) {
        username = username == null ? "" : username.trim();
        password = password == null ? "" : password.trim();
        fullName = fullName == null ? "" : fullName.trim();
        email = email == null ? "" : email.trim();

        if (username.isEmpty()) throw new IllegalArgumentException("username is required");
        if (password.length() < 8) throw new IllegalArgumentException("password too short");
        if (fullName.isEmpty()) throw new IllegalArgumentException("fullName is required");
        if (email.isEmpty()) throw new IllegalArgumentException("email is required");
        if (!EMAIL.matcher(email).matches()) throw new IllegalArgumentException("email invalid");

        return new User(username, password, fullName, email);
    }
}
