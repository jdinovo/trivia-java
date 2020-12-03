package dao;

import javabean.QuizQuestion;

import java.util.ArrayList;

public interface QuizQuestionDAO {
    public ArrayList<QuizQuestion> getAllQuizQuestions();
    public ArrayList<QuizQuestion> getQuestionsForQuiz(int quizId);
    public QuizQuestion getQuizQuestion(int quizQuestionId);
    public void updateQuizQuestion(QuizQuestion quizQuestion);
    public void deleteQuizQuestion(QuizQuestion quizQuestion);
    public void createQuizQuestion(QuizQuestion quizQuestion);
}
