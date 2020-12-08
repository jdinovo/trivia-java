package dao;

import javabean.QuizAnswer;

import java.util.ArrayList;

public interface QuizAnswerDAO {
    ArrayList<QuizAnswer> getAllQuizQuestions();
    ArrayList<QuizAnswer> getQuizAnswersForQuestion(int quizQuestionId);
    QuizAnswer getQuizQuestion(int quizAnswerId);
    void updateQuizQuestion(QuizAnswer quizAnswer);
    void deleteQuizQuestion(QuizAnswer quizAnswer);
    void createQuizQuestion(QuizAnswer quizAnswer);
}
