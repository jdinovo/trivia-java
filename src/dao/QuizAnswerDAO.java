package dao;

import javabean.QuizAnswer;

import java.util.ArrayList;

public interface QuizAnswerDAO {
    public ArrayList<QuizAnswer> getAllQuizQuestions();
    public ArrayList<QuizAnswer> getQuizAnswersForQuestion(int quizQuestionId);
    public QuizAnswer getQuizQuestion(int quizAnswerId);
    public void updateQuizQuestion(QuizAnswer quizAnswer);
    public void deleteQuizQuestion(QuizAnswer quizAnswer);
    public void createQuizQuestion(QuizAnswer quizAnswer);
}
