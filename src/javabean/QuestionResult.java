package javabean;

public class QuestionResult {
    private QuizQuestion quizQuestion;
    private QuestionAnswer questionAnswer;

    public QuestionResult() {
    }

    public QuestionResult(QuizQuestion quizQuestion, QuestionAnswer questionAnswer) {
        this.quizQuestion = quizQuestion;
        this.questionAnswer = questionAnswer;
    }

    public QuizQuestion getQuizQuestion() {
        return quizQuestion;
    }

    public QuestionAnswer getQuestionAnswer() {
        return questionAnswer;
    }
}
