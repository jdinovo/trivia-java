package tables;

import dao.QuizDAO;
import database.DBConst;
import database.Database;
import javabean.Quiz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QuizTable implements QuizDAO {
    private final Database db;
//    private final UserTable userTable;

    public QuizTable() {
        this.db = Database.getInstance();
//        this.userTable = new UserTable();
    }

    private ArrayList<Quiz> getAllFromDB(String query) {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        try {
            Statement getItems = db.getConnection().createStatement();
            ResultSet data = getItems.executeQuery(query);

            while(data.next()) {
                quizzes.add(new Quiz(data.getInt(DBConst.QUIZZES_COLUMN_ID),
//                        userTable.getUser(data.getInt(DBConst.QUIZZES_COLUMN_AUTHOR)),
                        data.getString(DBConst.QUIZZES_COLUMN_TITLE),
                        data.getString(DBConst.QUIZZES_COLUMN_DESCRIPTION)));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return quizzes;
    }

    @Override
    public ArrayList<Quiz> getAllQuizzes() {
        String query = "SELECT * FROM " + DBConst.TABLE_QUIZZES;
        return getAllFromDB(query);

    }

    @Override
    public Quiz getQuiz(int quizId) {
        String query = "SELECT * FROM " + DBConst.TABLE_QUIZZES + " WHERE " + DBConst.QUIZZES_COLUMN_ID + " = " + quizId;
        Quiz quiz = new Quiz();
        try {
            Statement getItem = db.getConnection().createStatement();
            ResultSet data = getItem.executeQuery(query);
            quiz = new Quiz(data.getInt(DBConst.QUIZZES_COLUMN_ID),
//                    userTable.getUser(data.getInt(DBConst.QUIZZES_COLUMN_AUTHOR)),
                    data.getString(DBConst.QUIZZES_COLUMN_TITLE),
                    data.getString(DBConst.QUIZZES_COLUMN_DESCRIPTION));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quiz;
    }

    @Override
    public void updateQuiz(Quiz quiz) {
        String query = "UPDATE " + DBConst.TABLE_QUIZZES + " SET "  +
//                DBConst.QUIZZES_COLUMN_AUTHOR + " = ?, " +
                DBConst.QUIZZES_COLUMN_TITLE + " = ?, " +
                DBConst.QUIZZES_COLUMN_DESCRIPTION + " = ? WHERE " + DBConst.QUIZZES_COLUMN_ID + " = " + quiz.getId();
        try {
            PreparedStatement updateItem = db.getConnection().prepareStatement(query);
//            updateItem.setInt(1, quiz.getAuthor().getId());
            updateItem.setString(1, quiz.getTitle());
            updateItem.setString(2, quiz.getDescription());
            updateItem.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteQuiz(Quiz quiz) {
        String query = "DELETE FROM " + DBConst.TABLE_QUIZZES + " WHERE " + DBConst.QUIZZES_COLUMN_ID + " = " + quiz.getId();
        try {
            db.getConnection().createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createQuiz(Quiz quiz) {
        String query = "INSERT INTO " + DBConst.TABLE_QUIZZES + " ("
//                + DBConst.QUIZZES_COLUMN_AUTHOR + ", "
                + DBConst.QUIZZES_COLUMN_TITLE + ", " +
                DBConst.QUIZZES_COLUMN_DESCRIPTION + ") VALUES (?, ?)";
        try {
            PreparedStatement createItem = db.getConnection().prepareStatement(query);
//            createItem.setInt(1, quiz.getAuthor().getId());
            createItem.setString(1, quiz.getTitle());
            createItem.setString(2, quiz.getDescription());
            createItem.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
