package org.example.repository;

import org.example.model.User;
import java.util.List;

public interface UserDao {
    User save(User user);
    //User findById(Integer id);
    User getById(Long id);
    User getUserByEmail(String email);
    User getUserByCredentials(String email,String password);
    boolean delete(User u);
    List<User> findAllUsers();

}

