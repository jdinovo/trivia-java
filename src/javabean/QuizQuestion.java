package javabean;

public class QuizQuestion {
    private int id;
    private String category;
    private String subcategory;
    private Difficulty difficulty;
    private String question;

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
        this.category = category;
        this.subcategory = subcategory;
        this.difficulty = difficulty;
        this.question = question;
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
        this.id = id;
        this.category = category;
        this.subcategory = subcategory;
        this.difficulty = difficulty;
        this.question = question;
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

    /**
     *
     * @return
     */
    public String getQuestion() {
        return question;
    }

    /**
     *
     * @param question
     */
    public void setQuestion(String question) {
        this.question = question;
    }
}
