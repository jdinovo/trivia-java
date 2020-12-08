package javabean;

public class Quiz {
    private int id;
    private User author;
    private String title;
    private String description;

    public Quiz() {
    }

    /**
     *
     * @param author - User
     * @param title - String
     * @param description - String
     */
    public Quiz(User author, String title, String description) {
        this.author = author;
        this.title = title;
        this.description = description;
    }

    /**
     *
     * @param id - int
     * @param author - User
     * @param title - String
     * @param description - String
     */
    public Quiz(int id, User author, String title, String description) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
    }

    /**
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return User
     */
    public User getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     *
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
