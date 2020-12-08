package javabean;

public class User {
    private int id;
    private String username;
    private String password;

    public User() {
    }

    /**
     *
     * @param username - String
     * @param password - String
     */
    public User(String username, String password) {
        this.id = -1;
        this.username = username;
        this.password = password;
    }

    /**
     *
     * @param id - int
     * @param username - String
     * @param password - String
     */
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
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
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

}
