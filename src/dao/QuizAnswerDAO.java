package dao;

import javabean.QuizAnswer;

import java.util.ArrayList;

public interface QuizAnswerDAO {
    ArrayList<QuizAnswer> getAllQuizAnswers();
    ArrayList<QuizAnswer> getQuizAnswersForQuestion(int quizQuestionId);
    QuizAnswer getQuizAnswer(int quizAnswerId);
    void updateQuizAnswer(QuizAnswer quizAnswer);
    void deleteQuizAnswer(QuizAnswer quizAnswer);
    void createQuizAnswer(QuizAnswer quizAnswer);
}
