package tables;

import dao.QuizAnswerDAO;
import database.DBConst;
import database.Database;
import javabean.QuizAnswer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QuizAnswerTable implements QuizAnswerDAO {

    private final Database db;

    public QuizAnswerTable() {
        this.db = Database.getInstance();
    }

    private ArrayList<QuizAnswer> getAllFromDB(String query) {
        ArrayList<QuizAnswer> answers = new ArrayList<>();
        QuizQuestionTable quizQuestionTable = new QuizQuestionTable();
        try {
            Statement getItems = db.getConnection().createStatement();
            ResultSet data = getItems.executeQuery(query);

            while(data.next()) {
                answers.add(new QuizAnswer(data.getInt(DBConst.QUIZ_ANSWERS_COLUMN_ID),
                        data.getString(DBConst.QUIZ_ANSWERS_COLUMN_ANSWER),
                        data.getBoolean(DBConst.QUIZ_ANSWERS_COLUMN_CORRECT),
                        quizQuestionTable.getQuizQuestion(data.getInt(DBConst.QUIZ_ANSWERS_COLUMN_QUIZ_QUESTION_ID))));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    @Override
    public ArrayList<QuizAnswer> getAllQuizQuestions() {
        String query = "SELECT * FROM " + DBConst.TABLE_QUIZ_ANSWERS;
        return getAllFromDB(query);
    }

    @Override
    public ArrayList<QuizAnswer> getQuizAnswersForQuestion(int quizQuestionId) {
        String query = "SELECT * FROM " + DBConst.TABLE_QUIZ_ANSWERS + " WHERE " +
                DBConst.QUIZ_ANSWERS_COLUMN_QUIZ_QUESTION_ID + " = " + quizQuestionId;
        return getAllFromDB(query);
    }

    @Override
    public QuizAnswer getQuizQuestion(int quizAnswerId) {
        String query = "SELECT * FROM " + DBConst.TABLE_QUIZ_ANSWERS + " WHERE " + DBConst.QUIZ_ANSWERS_COLUMN_ID + " = " + quizAnswerId;
        QuizAnswer answer = new QuizAnswer();
        QuizQuestionTable quizQuestionTable = new QuizQuestionTable();

        try {
            Statement getItem = db.getConnection().createStatement();
            ResultSet data = getItem.executeQuery(query);
            answer = new QuizAnswer(data.getInt(DBConst.QUIZ_ANSWERS_COLUMN_ID),
                    data.getString(DBConst.QUIZ_ANSWERS_COLUMN_ANSWER),
                    data.getBoolean(DBConst.QUIZ_ANSWERS_COLUMN_CORRECT),
                    quizQuestionTable.getQuizQuestion(data.getInt(DBConst.QUIZ_ANSWERS_COLUMN_QUIZ_QUESTION_ID)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    public void updateQuizQuestion(QuizAnswer quizAnswer) {
        String query = "UPDATE " + DBConst.TABLE_QUIZ_ANSWERS + " SET "  +
                DBConst.QUIZ_ANSWERS_COLUMN_ANSWER + " = ?, " +
                DBConst.QUIZ_ANSWERS_COLUMN_CORRECT + " = ?, " +
                DBConst.QUIZ_ANSWERS_COLUMN_QUIZ_QUESTION_ID + " = ? WHERE " + DBConst.QUIZ_ANSWERS_COLUMN_ID + " = " + quizAnswer.getId();
        try {
            PreparedStatement updateItem = db.getConnection().prepareStatement(query);
            updateItem.setString(1, quizAnswer.getText());
            updateItem.setBoolean(2, quizAnswer.isCorrect());
            updateItem.setInt(3, quizAnswer.getQuizQuestion().getId());
            updateItem.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteQuizQuestion(QuizAnswer quizAnswer) {
        String query = "DELETE FROM " + DBConst.TABLE_QUIZ_ANSWERS + " WHERE " + DBConst.QUIZ_ANSWERS_COLUMN_ID + " = " + quizAnswer.getId();
        try {
            db.getConnection().createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void createQuizQuestion(QuizAnswer quizAnswer) {
        String query = "INSERT INTO " + DBConst.TABLE_QUIZ_ANSWERS +
                " (" + DBConst.QUIZ_ANSWERS_COLUMN_ANSWER + ", " +
                DBConst.QUIZ_ANSWERS_COLUMN_CORRECT + ", " +
                DBConst.QUIZ_ANSWERS_COLUMN_QUIZ_QUESTION_ID + ") VALUES (?, ?, ?)";
        try {
            PreparedStatement createItem = db.getConnection().prepareStatement(query);
            createItem.setString(1, quizAnswer.getText());
            createItem.setBoolean(2, quizAnswer.isCorrect());
            createItem.setInt(3, quizAnswer.getQuizQuestion().getId());
            createItem.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
