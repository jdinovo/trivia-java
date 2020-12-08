package dao;

import javabean.QuizQuestion;

import java.util.ArrayList;

public interface QuizQuestionDAO {
    ArrayList<QuizQuestion> getAllQuizQuestions();
    ArrayList<QuizQuestion> getQuestionsForQuiz(int quizId);
    QuizQuestion getQuizQuestion(int quizQuestionId);
    void updateQuizQuestion(QuizQuestion quizQuestion);
    void deleteQuizQuestion(QuizQuestion quizQuestion);
    void createQuizQuestion(QuizQuestion quizQuestion);
}
