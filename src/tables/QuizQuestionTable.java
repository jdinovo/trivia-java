package tables;

import dao.QuizQuestionDAO;
import database.DBConst;
import database.Database;
import javabean.Difficulty;
import javabean.QuizQuestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QuizQuestionTable implements QuizQuestionDAO {
    private final Database db;

    public QuizQuestionTable() {
        this.db = Database.getInstance();
    }

    private ArrayList<QuizQuestion> getAllFromDB(String query) {
        ArrayList<QuizQuestion> questions = new ArrayList<>();
        try {
            Statement getItems = db.getConnection().createStatement();
            ResultSet data = getItems.executeQuery(query);

            while(data.next()) {
                questions.add(new QuizQuestion(data.getInt(DBConst.QUIZ_QUESTIONS_COLUMN_ID),
                        data.getString(DBConst.QUIZ_QUESTIONS_COLUMN_CATEGORY),
                        data.getString(DBConst.QUIZ_QUESTIONS_COLUMN_SUBCATEGORY),
                        Difficulty.fromInt(data.getInt(DBConst.QUIZ_QUESTIONS_COLUMN_DIFFICULTY)),
                        data.getString(DBConst.QUIZ_QUESTIONS_COLUMN_QUESTION)));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    @Override
    public ArrayList<QuizQuestion> getAllQuizQuestions() {
        String query = "SELECT * FROM " + DBConst.TABLE_QUIZ_QUESTIONS;
        return getAllFromDB(query);
    }

    @Override
    public ArrayList<QuizQuestion> getQuestionsForQuiz(int quizId) {

        String query = "SELECT " + DBConst.TABLE_QUIZ_QUESTIONS +".* FROM " + DBConst.TABLE_QUIZ_QUESTIONS +
                " INNER JOIN " + DBConst.TABLE_QUESTION_QUIZ + " ON " + DBConst.TABLE_QUIZ_QUESTIONS + "." +
                DBConst.QUIZ_QUESTIONS_COLUMN_ID + " = " + DBConst.TABLE_QUESTION_QUIZ + "." +
                DBConst.QUESTION_QUIZ_COLUMN_QUESTION_ID + " WHERE " + DBConst.QUESTION_QUIZ_COLUMN_QUIZ_ID + " = " + quizId;
        return getAllFromDB(query);
    }

    @Override
    public QuizQuestion getQuizQuestion(int quizQuestionId) {
        String query = "SELECT * FROM " + DBConst.TABLE_QUIZ_QUESTIONS + " WHERE " + DBConst.QUIZ_QUESTIONS_COLUMN_ID + " = " + quizQuestionId;
        QuizQuestion question = new QuizQuestion();
        try {
            Statement getItem = db.getConnection().createStatement();
            ResultSet data = getItem.executeQuery(query);
            question = new QuizQuestion(data.getInt(DBConst.QUIZ_QUESTIONS_COLUMN_ID),
                    data.getString(DBConst.QUIZ_QUESTIONS_COLUMN_CATEGORY),
                    data.getString(DBConst.QUIZ_QUESTIONS_COLUMN_SUBCATEGORY),
                    Difficulty.fromInt(data.getInt(DBConst.QUIZ_QUESTIONS_COLUMN_DIFFICULTY)),
                    data.getString(DBConst.QUIZ_QUESTIONS_COLUMN_QUESTION));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }

    @Override
    public void updateQuizQuestion(QuizQuestion quizQuestion) {
        String query = "UPDATE " + DBConst.TABLE_QUIZ_QUESTIONS + " SET "  +
                DBConst.QUIZ_QUESTIONS_COLUMN_CATEGORY + " = ?, " +
                DBConst.QUIZ_QUESTIONS_COLUMN_SUBCATEGORY + " = ?, " +
                DBConst.QUIZ_QUESTIONS_COLUMN_DIFFICULTY + " = ?, " +
                DBConst.QUIZ_QUESTIONS_COLUMN_QUESTION + " = ? WHERE " + DBConst.QUIZ_QUESTIONS_COLUMN_ID + " = " + quizQuestion.getId();
        try {
            PreparedStatement updateItem = db.getConnection().prepareStatement(query);
            updateItem.setString(1, quizQuestion.getCategory());
            updateItem.setString(2, quizQuestion.getSubcategory());
            updateItem.setInt(3, quizQuestion.getDifficulty().ordinal());
            updateItem.setString(4, quizQuestion.getText());
            updateItem.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteQuizQuestion(QuizQuestion quizQuestion) {
        String query = "DELETE FROM " + DBConst.TABLE_QUIZ_QUESTIONS + " WHERE " + DBConst.QUIZ_QUESTIONS_COLUMN_ID + " = " + quizQuestion.getId();
        try {
            db.getConnection().createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int createQuizQuestion(QuizQuestion quizQuestion) {
        String query = "INSERT INTO " + DBConst.TABLE_QUIZ_QUESTIONS +
                " (" + DBConst.QUIZ_QUESTIONS_COLUMN_CATEGORY + ", " +
                DBConst.QUIZ_QUESTIONS_COLUMN_SUBCATEGORY + ", " +
                DBConst.QUIZ_QUESTIONS_COLUMN_DIFFICULTY + ", " +
                DBConst.QUIZ_QUESTIONS_COLUMN_QUESTION + ") VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement createItem = db.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            createItem.setString(1, quizQuestion.getCategory());
            createItem.setString(2, quizQuestion.getSubcategory());
            createItem.setInt(3, quizQuestion.getDifficulty().ordinal());
            createItem.setString(4, quizQuestion.getText());
            createItem.execute();

            ResultSet rs = createItem.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            rs.close();

            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<QuizQuestion> searchQuizQuestions(String searchQuery) {
        String query = "SELECT * FROM " + DBConst.TABLE_QUIZ_QUESTIONS + " WHERE " + DBConst.QUIZ_QUESTIONS_COLUMN_QUESTION + " LIKE " + "'%" + searchQuery + "%'";
        return getAllFromDB(query);
    }
}
