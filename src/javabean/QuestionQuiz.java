package javabean;

import java.util.Objects;

public class QuestionQuiz {
    private int id;
    private int quizId;
    private int questionId;
//    private int orderNumber;

    public QuestionQuiz() {

    }

    public QuestionQuiz(int quizId, int questionId) {
        this.id = -1;
        this.quizId = quizId;
        this.questionId = questionId;
//        this.orderNumber = orderNumber;
    }

    public QuestionQuiz(int id, int quizId, int questionId) {
        this.id = id;
        this.quizId = quizId;
        this.questionId = questionId;
//        this.orderNumber = orderNumber;
    }

    public int getId() {
        return id;
    }

    public int getQuizId() {
        return quizId;
    }

    public int getQuestionId() {
        return questionId;
    }

//    public int getOrderNumber() {
//        return orderNumber;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionQuiz)) return false;
        QuestionQuiz that = (QuestionQuiz) o;
        return id == that.id &&
                quizId == that.quizId &&
                questionId == that.questionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quizId, questionId);
    }
}
