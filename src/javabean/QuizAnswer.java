package javabean;

public class QuizAnswer extends QuizData {
    private boolean correct;
    private QuizQuestion quizQuestion;


    public QuizAnswer() {

    }

    /**
     *
     * @param answer
     * @param correct
     */
    public QuizAnswer(String answer, boolean correct) {
        super(answer);
        this.correct = correct;
        this.quizQuestion = null;
    }

    /**
     *
     * @param id
     * @param answer
     * @param correct
     * @param quizQuestion
     */
    public QuizAnswer(int id, String answer, boolean correct, QuizQuestion quizQuestion) {
        super(id, answer);
        this.correct = correct;
        this.quizQuestion = quizQuestion;
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

    @Override
    public String toString() {
        return  (correct ? "T" : "F") + " " + super.toString();
    }
}
