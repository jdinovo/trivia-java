package tables;

import dao.QuizQuestionDAO;
import javabean.QuizQuestion;

import java.util.ArrayList;

public class QuizQuestionTable implements QuizQuestionDAO {
    @Override
    public ArrayList<QuizQuestion> getAllQuizQuestions() {
        return null;
    }

    @Override
    public ArrayList<QuizQuestion> getQuestionsForQuiz(int quizId) {
        return null;
    }

    @Override
    public QuizQuestion getQuizQuestion(int quizQuestionId) {
        return null;
    }

    @Override
    public void updateQuizQuestion(QuizQuestion quizQuestion) {

    }

    @Override
    public void deleteQuizQuestion(QuizQuestion quizQuestion) {

    }

    @Override
    public void createQuizQuestion(QuizQuestion quizQuestion) {

    }
}
