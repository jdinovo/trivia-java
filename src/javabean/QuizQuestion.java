package javabean;

import tables.QuestionAnswerTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class QuizQuestion extends QuizData {
    private String category;
    private String subcategory;
    private Difficulty difficulty;
    private ArrayList<QuestionAnswer> answers;

    public QuizQuestion() {
    }

    /**
     *
     * @param category
     * @param subcategory
     * @param difficulty
     * @param question
     */
    public QuizQuestion(String category, String subcategory, Difficulty difficulty, String question) {
        super(question);
        this.category = category;
        this.subcategory = subcategory;
        this.difficulty = difficulty;
    }

    /**
     *
     * @param id
     * @param category
     * @param subcategory
     * @param difficulty
     * @param question
     */
    public QuizQuestion(int id, String category, String subcategory, Difficulty difficulty, String question) {
        super(id, question);
        this.category = category;
        this.subcategory = subcategory;
        this.difficulty = difficulty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuizQuestion)) return false;
        QuizQuestion that = (QuizQuestion) o;
        return category.equals(that.category) &&
                subcategory.equals(that.subcategory) &&
                difficulty == that.difficulty &&
                super.getId() == that.getId() &&
                super.getText().equals(that.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId(), super.getText(), category, subcategory, difficulty);
    }

    /**
     *
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return
     */
    public String getSubcategory() {
        return subcategory;
    }

    /**
     *
     * @param subcategory
     */
    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    /**
     *
     * @return
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     *
     * @param difficulty
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    private void loadAllAnswers() {
        QuestionAnswerTable questionAnswerTable = new QuestionAnswerTable();
        this.answers = questionAnswerTable.getAnswersForQuestion(getId());
    }

    private void randomizeAnswers() {
        Collections.shuffle(this.answers);
    }

    public QuestionAnswer[] getAnswers(boolean randomize) {
        loadAllAnswers();

        if (randomize) {
            randomizeAnswers();
        }

        return this.answers.toArray(new QuestionAnswer[answers.size()]);
    }

    @Override
    public String toString() {
        return "\""+ super.toString() + "\" | " + difficulty + " | " + category + (subcategory.isEmpty() ? "" : ": " + subcategory);
    }
}
