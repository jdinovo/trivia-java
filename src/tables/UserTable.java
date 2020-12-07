package tables;

import dao.UserDAO;
import database.DBConst;
import database.Database;
import javabean.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserTable implements UserDAO {
    private Database db = Database.getInstance();
    private ArrayList<User> users;

    private ArrayList<User> getAllFromDB(String query) {
        users = new ArrayList<>();
        try {
            Statement getItems = db.getConnection().createStatement();
            ResultSet data = getItems.executeQuery(query);

            while(data.next()) {
                users.add(new User(data.getInt(DBConst.USERS_COLUMN_ID),
                        data.getString(DBConst.USERS_COLUMN_USERNAME),
                        data.getString(DBConst.USERS_COLUMN_PASSWORD)));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        String query = "SELECT * FROM " + DBConst.TABLE_USERS;
        return getAllFromDB(query);
    }

    @Override
    public User getUser(int userId) {
        String query = "SELECT * FROM " + DBConst.TABLE_USERS + " WHERE " + DBConst.USERS_COLUMN_ID + " = " + userId;
        User user = new User();
        try {
            Statement getItem = db.getConnection().createStatement();
            ResultSet data = getItem.executeQuery(query);
            user = new User(data.getInt(DBConst.USERS_COLUMN_ID),
                    data.getString(DBConst.USERS_COLUMN_USERNAME),
                    data.getString(DBConst.USERS_COLUMN_PASSWORD));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        String query = "UPDATE " + DBConst.TABLE_USERS + " SET "  +
                DBConst.USERS_COLUMN_USERNAME + " = ?, " +
                DBConst.USERS_COLUMN_PASSWORD + " = ? WHERE " + DBConst.USERS_COLUMN_ID + " = " + user.getId();
        try {
            PreparedStatement updateItem = db.getConnection().prepareStatement(query);
            updateItem.setString(1, user.getUsername());
            updateItem.setString(2, user.getPassword());
            updateItem.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        String query = "DELETE FROM " + DBConst.TABLE_USERS + " WHERE " + DBConst.USERS_COLUMN_ID + " = " + user.getId();
        try {
            db.getConnection().createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createUser(User user) {
        String query = "INSERT INTO " + DBConst.TABLE_USERS +
                " (" + DBConst.USERS_COLUMN_USERNAME + ", " +
                DBConst.USERS_COLUMN_PASSWORD + ") VALUES (?, ?)";
        try {
            PreparedStatement createItem = db.getConnection().prepareStatement(query);
            createItem.setString(1, user.getUsername());
            createItem.setString(2, user.getPassword());
            createItem.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
