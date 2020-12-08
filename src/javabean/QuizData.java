package javabean;

public abstract class QuizData {
    private final int id;
    private final String text;

    public QuizData() {
        this.id = -1;
        this.text = "";
    }

    /**
     *
     * @param text - text data to be displayed
     */
    public QuizData(String text) {
        this.id = -1;
        this.text = text;
    }

    /**
     *
     * @param id - id of data in db
     * @param text - text data to be displayed
     */
    public QuizData(int id, String text) {
        this.id = id;
        this.text = text;
    }

    /**
     *
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return String text data
     */
    public String getText() {
        return text;
    }
}
