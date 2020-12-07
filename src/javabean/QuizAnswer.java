package javabean;

public class QuizAnswer {
    private int id;
    private String answer;
    private boolean correct;
    private QuizQuestion quizQuestion;

    public QuizAnswer() {
    }

    /**
     *
     * @param answer
     * @param correct
     * @param quizQuestion
     */
    public QuizAnswer(String answer, boolean correct, QuizQuestion quizQuestion) {
        this.answer = answer;
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
    public QuizAnswer(int id, String answer, boolean correct, QuizQuestion quizQuestion) {
        this.id = id;
        this.answer = answer;
        this.correct = correct;
        this.quizQuestion = quizQuestion;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getAnswer() {
        return answer;
    }

    /**
     *
     * @param answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
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
}
