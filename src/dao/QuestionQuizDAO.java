package dao;

import javabean.QuestionQuiz;

import java.util.ArrayList;

public interface QuestionQuizDAO {
    ArrayList<QuestionQuiz> getQuizQuestionRelations(int quizId);
    QuestionQuiz getQuestionQuiz(int quizId, int questionId);
    void updateQuestionQuiz(QuestionQuiz questionQuiz);
    void deleteQuestionQuiz(QuestionQuiz questionQuiz);
    void createQuestionQuiz(QuestionQuiz questionQuiz);
}
