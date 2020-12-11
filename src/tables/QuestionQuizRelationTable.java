package tables;

import dao.QuestionQuizDAO;
import database.DBConst;
import database.Database;
import javabean.QuestionQuiz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QuestionQuizRelationTable implements QuestionQuizDAO {
    private final Database db;

    public QuestionQuizRelationTable() {
        this.db = Database.getInstance();
    }

    private ArrayList<QuestionQuiz> getAllFromDB(String query) {
        ArrayList<QuestionQuiz> questionQuizzes = new ArrayList<>();
        try {
            Statement getItems = db.getConnection().createStatement();
            ResultSet data = getItems.executeQuery(query);

            while(data.next()) {
                questionQuizzes.add(new QuestionQuiz(data.getInt(DBConst.QUESTION_QUIZ_COLUMN_ID),
                        data.getInt(DBConst.QUESTION_QUIZ_COLUMN_QUIZ_ID),
                        data.getInt(DBConst.QUESTION_QUIZ_COLUMN_QUESTION_ID)
//                        data.getInt(DBConst.QUESTION_QUIZ_COLUMN_NUMBER),
                        ));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return questionQuizzes;
    }

    public boolean quizHasQuestion(int quizId, int questionId) {
        String query = "SELECT 1 FROM " + DBConst.TABLE_QUESTION_QUIZ + " WHERE "
                + DBConst.QUESTION_QUIZ_COLUMN_QUIZ_ID + " = " + quizId
                + " AND " + DBConst.QUESTION_QUIZ_COLUMN_QUESTION_ID + " = " + questionId;
        boolean exists = false;
        try {
            Statement getItem = db.getConnection().createStatement();
            ResultSet data = getItem.executeQuery(query);

            exists = (data.getFetchSize() > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public QuestionQuiz getQuestionQuiz(int quizId, int questionId) {
        String query = "SELECT * FROM " + DBConst.TABLE_QUESTION_QUIZ + " WHERE "
                + DBConst.QUESTION_QUIZ_COLUMN_QUIZ_ID + " = " + quizId
                + " AND " + DBConst.QUESTION_QUIZ_COLUMN_QUESTION_ID + " = " + questionId;
        QuestionQuiz questionQuiz = new QuestionQuiz();
        try {
            Statement getItem = db.getConnection().createStatement();
            ResultSet data = getItem.executeQuery(query);
            if (!data.isClosed()) {
                questionQuiz = new QuestionQuiz(data.getInt(DBConst.QUESTION_QUIZ_COLUMN_ID),
                        data.getInt(DBConst.QUESTION_QUIZ_COLUMN_QUIZ_ID),
                        data.getInt(DBConst.QUESTION_QUIZ_COLUMN_QUESTION_ID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionQuiz;
    }

    @Override
    public ArrayList<QuestionQuiz> getQuizQuestionRelations(int quizId) {
        String query = "SELECT * FROM " + DBConst.TABLE_QUESTION_QUIZ + " WHERE " + DBConst.QUESTION_QUIZ_COLUMN_QUIZ_ID + " = " + quizId;
        return getAllFromDB(query);
    }

    @Override
    public void updateQuestionQuiz(QuestionQuiz questionQuiz) {
        String query = "UPDATE " + DBConst.TABLE_QUESTION_QUIZ + " SET "  +
                DBConst.QUESTION_QUIZ_COLUMN_QUIZ_ID + " = ?, " +
                DBConst.QUESTION_QUIZ_COLUMN_QUESTION_ID + " = ? " +
//                DBConst.QUESTION_QUIZ_COLUMN_NUMBER + " = ?, " +
                " WHERE " + DBConst.QUESTION_QUIZ_COLUMN_ID + " = " + questionQuiz.getId();
        try {
            PreparedStatement updateItem = db.getConnection().prepareStatement(query);
            updateItem.setInt(1, questionQuiz.getQuizId());
            updateItem.setInt(2, questionQuiz.getQuestionId());
            updateItem.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteQuestionQuiz(QuestionQuiz questionQuiz) {
        String query = "DELETE FROM " + DBConst.TABLE_QUESTION_QUIZ + " WHERE " + DBConst.QUESTION_QUIZ_COLUMN_ID + " = " + questionQuiz.getId();
        try {
            db.getConnection().createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createQuestionQuiz(QuestionQuiz questionQuiz) {

        // only create if it does not already exist
//        if (quizHasQuestion(questionQuiz.getQuizId(), questionQuiz.getQuestionId())) {

            String query = " INSERT OR IGNORE INTO " + DBConst.TABLE_QUESTION_QUIZ +
                    " (" + DBConst.QUESTION_QUIZ_COLUMN_QUIZ_ID + ", " +
                    DBConst.QUESTION_QUIZ_COLUMN_QUESTION_ID + ") VALUES (?, ?)";
            try {
                PreparedStatement createItem = db.getConnection().prepareStatement(query);
                createItem.setInt(1, questionQuiz.getQuizId());
                createItem.setInt(2, questionQuiz.getQuestionId());
                createItem.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
//        }
    }
}
