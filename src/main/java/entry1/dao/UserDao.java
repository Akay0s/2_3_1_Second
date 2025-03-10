package entry1.dao;

import entry1.model.User;

import java.util.List;

public interface UserDao {
    void save(User user);

    User findById(Long userId);

    List<User> findAll();

    void update(User user);

    void delete(Long userId);
}
