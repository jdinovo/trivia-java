package dao;

import javabean.Quiz;

import java.util.ArrayList;

public interface QuizDAO {
    ArrayList<Quiz> getAllQuizzes();
    Quiz getQuiz(int quizId);
    void updateQuiz(Quiz quiz);
    void deleteQuiz(Quiz quiz);
    int createQuiz(Quiz quiz);
}
