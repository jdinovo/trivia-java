package dao;

import javabean.Quiz;

import java.util.ArrayList;

public interface QuizDAO {
    ArrayList<Quiz> getAllQuizQuestions();
    Quiz getQuizQuestion(int quizId);
    void updateQuizQuestion(Quiz quiz);
    void deleteQuizQuestion(Quiz quiz);
    void createQuizQuestion(Quiz quiz);
}
