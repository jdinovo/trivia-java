package dao;

import javabean.User;

import java.util.ArrayList;

public interface UserDAO {
    ArrayList<User> getAllUsers();
    User getUser(int userId);
    void updateUser(User user);
    void deleteUser(User user);
    void createUser(User user);
}
