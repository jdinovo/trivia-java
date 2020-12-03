package dao;

import javabean.Quiz;

import java.util.ArrayList;

public interface QuizDAO {
    public ArrayList<Quiz> getAllQuizQuestions();
    public Quiz getQuizQuestion(int quizId);
    public void updateQuizQuestion(Quiz quiz);
    public void deleteQuizQuestion(Quiz quiz);
    public void createQuizQuestion(Quiz quiz);
}
