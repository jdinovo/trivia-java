package javabean;

public class QuizQuestion extends QuizData {
    private String category;
    private String subcategory;
    private Difficulty difficulty;

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

    @Override
    public String toString() {
        return "\""+ super.toString() + "\" " + difficulty + " " + category + ": " + subcategory;
    }
}
