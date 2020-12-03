package tables;

import dao.UserDAO;
import javabean.User;

import java.util.ArrayList;

public class UserTable implements UserDAO {
    @Override
    public ArrayList<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUser(int userId) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void createUser(User user) {

    }
}
