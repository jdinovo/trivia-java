package javabean;

public class QuestionQuiz {
    private int id;
    private int quizId;
    private int questionId;
    private int orderNumber;

    public QuestionQuiz(int quizId, int questionId, int orderNumber) {
        this.id = -1;
        this.quizId = quizId;
        this.questionId = questionId;
        this.orderNumber = orderNumber;
    }

    public QuestionQuiz(int id, int quizId, int questionId, int orderNumber) {
        this.id = id;
        this.quizId = quizId;
        this.questionId = questionId;
        this.orderNumber = orderNumber;
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

    public int getOrderNumber() {
        return orderNumber;
    }

}
