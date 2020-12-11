package javabean;

import tables.QuizQuestionTable;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz {
    private int id;
    private User author;
    private String title;
    private String description;
    private ArrayList<QuizQuestion> questions;

    public Quiz() {
    }

    /**
     *
     * @param author - User
     * @param title - String
     * @param description - String
     */
    public Quiz(String title, String description) {
        this.id = -1;
//        this.author = author;
        this.title = title;
        this.description = description;
        this.questions = new ArrayList<>();
    }

    /**
     *
     * @param id - int
     * @param author - User
     * @param title - String
     * @param description - String
     */
    public Quiz(int id, String title, String description) {
        this.id = id;
//        this.author = author;
        this.title = title;
        this.description = description;

        this.questions = new ArrayList<>();
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

    private void loadAllQuestions() {
        QuizQuestionTable quizQuestionTable = new QuizQuestionTable();
        this.questions = quizQuestionTable.getQuestionsForQuiz(this.id);
    }

    private void randomizeQuestions() {
        Collections.shuffle(this.questions);
    }

    public ArrayList<QuizQuestion> getQuestions(boolean randomize) {
        loadAllQuestions();

        if (randomize) {
            randomizeQuestions();
        }

        return this.questions;
    }
}
