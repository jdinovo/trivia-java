package tables;

import dao.QuestionAnswerDAO;
import database.DBConst;
import database.Database;
import javabean.QuestionAnswer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QuestionAnswerTable implements QuestionAnswerDAO {

    private final Database db;

    public QuestionAnswerTable() {
        this.db = Database.getInstance();
    }

    private ArrayList<QuestionAnswer> getAllFromDB(String query) {
        ArrayList<QuestionAnswer> answers = new ArrayList<>();
        QuizQuestionTable quizQuestionTable = new QuizQuestionTable();
        try {
            Statement getItems = db.getConnection().createStatement();
            ResultSet data = getItems.executeQuery(query);

            while(data.next()) {
                answers.add(new QuestionAnswer(data.getInt(DBConst.QUESTION_ANSWERS_COLUMN_ID),
                        data.getString(DBConst.QUESTION_ANSWERS_COLUMN_ANSWER),
                        data.getBoolean(DBConst.QUESTION_ANSWERS_COLUMN_CORRECT),
                        quizQuestionTable.getQuizQuestion(data.getInt(DBConst.QUESTION_ANSWERS_COLUMN_QUIZ_QUESTION_ID))));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    @Override
    public ArrayList<QuestionAnswer> getAllQuestionAnswers() {
        String query = "SELECT * FROM " + DBConst.TABLE_QUESTION_ANSWERS;
        return getAllFromDB(query);
    }

    @Override
    public ArrayList<QuestionAnswer> getAnswersForQuestion(int quizQuestionId) {
        String query = "SELECT * FROM " + DBConst.TABLE_QUESTION_ANSWERS + " WHERE " +
                DBConst.QUESTION_ANSWERS_COLUMN_QUIZ_QUESTION_ID + " = " + quizQuestionId;
        return getAllFromDB(query);
    }

    @Override
    public QuestionAnswer getQuestionAnswer(int questionAnswerId) {
        String query = "SELECT * FROM " + DBConst.TABLE_QUESTION_ANSWERS + " WHERE " + DBConst.QUESTION_ANSWERS_COLUMN_ID + " = " + questionAnswerId;
        QuestionAnswer answer = new QuestionAnswer();
        QuizQuestionTable quizQuestionTable = new QuizQuestionTable();

        try {
            Statement getItem = db.getConnection().createStatement();
            ResultSet data = getItem.executeQuery(query);
            answer = new QuestionAnswer(data.getInt(DBConst.QUESTION_ANSWERS_COLUMN_ID),
                    data.getString(DBConst.QUESTION_ANSWERS_COLUMN_ANSWER),
                    data.getBoolean(DBConst.QUESTION_ANSWERS_COLUMN_CORRECT),
                    quizQuestionTable.getQuizQuestion(data.getInt(DBConst.QUESTION_ANSWERS_COLUMN_QUIZ_QUESTION_ID)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    public void updateQuestionAnswer(QuestionAnswer questionAnswer) {
        String query = "UPDATE " + DBConst.TABLE_QUESTION_ANSWERS + " SET "  +
                DBConst.QUESTION_ANSWERS_COLUMN_ANSWER + " = ?, " +
                DBConst.QUESTION_ANSWERS_COLUMN_CORRECT + " = ?, " +
                DBConst.QUESTION_ANSWERS_COLUMN_QUIZ_QUESTION_ID + " = ? WHERE " + DBConst.QUESTION_ANSWERS_COLUMN_ID + " = " + questionAnswer.getId();
        try {
            PreparedStatement updateItem = db.getConnection().prepareStatement(query);
            updateItem.setString(1, questionAnswer.getText());
            updateItem.setBoolean(2, questionAnswer.isCorrect());
            updateItem.setInt(3, questionAnswer.getQuizQuestion().getId());
            updateItem.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteQuestionAnswer(QuestionAnswer questionAnswer) {
        String query = "DELETE FROM " + DBConst.TABLE_QUESTION_ANSWERS + " WHERE " + DBConst.QUESTION_ANSWERS_COLUMN_ID + " = " + questionAnswer.getId();
        try {
            db.getConnection().createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void createQuestionAnswer(QuestionAnswer questionAnswer) {
        String query = "INSERT INTO " + DBConst.TABLE_QUESTION_ANSWERS +
                " (" + DBConst.QUESTION_ANSWERS_COLUMN_ANSWER + ", " +
                DBConst.QUESTION_ANSWERS_COLUMN_CORRECT + ", " +
                DBConst.QUESTION_ANSWERS_COLUMN_QUIZ_QUESTION_ID + ") VALUES (?, ?, ?)";
        try {
            PreparedStatement createItem = db.getConnection().prepareStatement(query);
            createItem.setString(1, questionAnswer.getText());
            createItem.setBoolean(2, questionAnswer.isCorrect());
            createItem.setInt(3, questionAnswer.getQuizQuestion().getId());
            createItem.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
