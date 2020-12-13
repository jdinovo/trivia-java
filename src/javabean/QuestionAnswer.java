package javabean;

import org.json.simple.JSONObject;

import java.util.Objects;

public class QuestionAnswer extends QuizData {
    private boolean correct;
    private QuizQuestion quizQuestion;


    public QuestionAnswer() {

    }

    /**
     *
     * @param answer
     * @param correct
     */
    public QuestionAnswer(String answer, boolean correct) {
        super(answer);
        this.correct = correct;
        this.quizQuestion = null;
    }

    /**
     *
     * @param answer
     * @param correct
     * @param quizQuestion
     */
    public QuestionAnswer(String answer, boolean correct, QuizQuestion quizQuestion) {
        super(answer);
        this.correct = correct;
        this.quizQuestion = quizQuestion;
    }

    /**
     *
     * @param id
     * @param answer
     * @param correct
     * @param quizQuestion
     */
    public QuestionAnswer(int id, String answer, boolean correct, QuizQuestion quizQuestion) {
        super(id, answer);
        this.correct = correct;
        this.quizQuestion = quizQuestion;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionAnswer)) return false;
        QuestionAnswer that = (QuestionAnswer) o;
        return getId() == that.getId() &&
                getText().equals(that.getText()) &&
                quizQuestion.equals(that.quizQuestion) &&
                correct == that.correct;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), super.getText(), quizQuestion, getText());
    }

    /**
     *
     * @return
     */
    public boolean isCorrect() {
        return correct;
    }

    /**
     *
     * @param correct
     */
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    /**
     *
     * @return
     */
    public QuizQuestion getQuizQuestion() {
        return quizQuestion;
    }

    /**
     *
     * @param quizQuestion
     */
    public void setQuizQuestion(QuizQuestion quizQuestion) {
        this.quizQuestion = quizQuestion;
    }

    public static QuestionAnswer jsonToQuestionAnswer(JSONObject object, QuizQuestion question) {
        return new QuestionAnswer(object.get("answer").toString(), (boolean) object.get("correct"), question);
    }

    public static JSONObject questionAnswerToJson(QuestionAnswer questionAnswer) {
        JSONObject answerObject = new JSONObject();

        answerObject.put("answer", questionAnswer.getText());
        answerObject.put("correct", questionAnswer.isCorrect());

        return answerObject;
    }

    @Override
    public String toString() {
        return  (correct ? "Correct" : "Incorrect") + " | " + super.toString();
    }
}
