package tables;

import dao.QuestionQuizDAO;
import javabean.QuestionQuiz;

import java.util.ArrayList;

public class QuestionQuizTable implements QuestionQuizDAO {
    @Override
    public ArrayList<QuestionQuiz> getQuestionQuizzes(int questionId) {
        return null;
    }

    @Override
    public ArrayList<QuestionQuiz> getQuizQuestions(int quizId) {
        return null;
    }

    @Override
    public void updateQuestionQuiz(QuestionQuiz questionQuiz) {

    }

    @Override
    public void deleteQuestionQuiz(QuestionQuiz questionQuiz) {

    }

    @Override
    public void createQuestionQuiz(QuestionQuiz questionQuiz) {

    }
}
