package dao;

import javabean.User;

import java.util.ArrayList;

public interface UserDAO {
    public ArrayList<User> getAllUsers();
    public User getUser(int userId);
    public void updateUser(User user);
    public void deleteUser(User user);
    public void createUser(User user);
}
