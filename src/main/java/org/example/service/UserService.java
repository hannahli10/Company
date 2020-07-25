package org.example.service;

import org.example.model.User;
import org.example.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired private UserDao userDao;
    public User save(User user){return userDao.save(user);};
    public User findById(Integer id){return userDao.findById(id);};
    public User getById(Long id){return userDao.getById(id);};
    public User getUserByEmail(String email){return userDao.getUserByEmail(email);};
    public User getUserByCredentials(String email,String password){return userDao.getUserByCredentials(email,password);};
    public boolean delete(User u){return userDao.delete(u);};
    public List<User> getAllUsers(){
        List<User> userList = userDao.findAllUsers();
        return userList;
    };
}
