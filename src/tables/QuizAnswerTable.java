package tables;

import dao.QuizAnswerDAO;
import javabean.QuizAnswer;

import java.util.ArrayList;

public class QuizAnswerTable implements QuizAnswerDAO {

    @Override
    public ArrayList<QuizAnswer> getAllQuizQuestions() {
        return null;
    }

    @Override
    public ArrayList<QuizAnswer> getQuizAnswersForQuestion(int quizQuestionId) {
        return null;
    }

    @Override
    public QuizAnswer getQuizQuestion(int quizAnswerId) {
        return null;
    }

    @Override
    public void updateQuizQuestion(QuizAnswer quizAnswer) {

    }

    @Override
    public void deleteQuizQuestion(QuizAnswer quizAnswer) {

    }

    @Override
    public void createQuizQuestion(QuizAnswer quizAnswer) {

    }
}
