package dao;

import javabean.QuestionQuiz;

import java.util.ArrayList;

public interface QuestionQuizDAO {
    ArrayList<QuestionQuiz> getQuestionQuizzes(int questionId);
    ArrayList<QuestionQuiz> getQuizQuestions(int quizId);
    void updateQuestionQuiz(QuestionQuiz questionQuiz);
    void deleteQuestionQuiz(QuestionQuiz questionQuiz);
    void createQuestionQuiz(QuestionQuiz questionQuiz);
}
