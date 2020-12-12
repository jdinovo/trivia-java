package javabean;

public abstract class QuizData {
    private int id;
    private String text;

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

    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return String text data
     */
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
