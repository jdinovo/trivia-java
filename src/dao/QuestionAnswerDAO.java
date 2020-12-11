package dao;

import javabean.QuestionAnswer;

import java.util.ArrayList;

public interface QuestionAnswerDAO {
    ArrayList<QuestionAnswer> getAllQuestionAnswers();
    ArrayList<QuestionAnswer> getAnswersForQuestion(int quizQuestionId);
    QuestionAnswer getQuestionAnswer(int questionAnswerId);
    void updateQuestionAnswer(QuestionAnswer questionAnswer);
    void deleteQuestionAnswer(QuestionAnswer questionAnswer);
    void createQuestionAnswer(QuestionAnswer questionAnswer);
}
