package database;

/**
 *
 * DBConst contains database login information and table information
 *
 */
public class DBConst {
    public static final String TABLE_USERS = "users";
    public static final String TABLE_QUIZZES = "quizzes";
    public static final String TABLE_QUIZ_ANSWERS = "quiz_answers";
    public static final String TABLE_QUIZ_QUESTIONS = "quiz_questions";
    public static final String TABLE_QUESTION_QUIZ = "question_quiz";

    public static final String USERS_COLUMN_ID = "id";
    public static final String USERS_COLUMN_USERNAME = "username";
    public static final String USERS_COLUMN_PASSWORD = "password";

    public static final String QUIZZES_COLUMN_ID = "id";
    public static final String QUIZZES_COLUMN_AUTHOR = "author";
    public static final String QUIZZES_COLUMN_TITLE = "title";
    public static final String QUIZZES_COLUMN_DESCRIPTION = "description";
    public static final String QUIZZES_COLUMN_TIME_LIMIT = "time_limit";

    public static final String QUIZ_ANSWERS_COLUMN_ID = "id";
    public static final String QUIZ_ANSWERS_COLUMN_ANSWER = "answer";
    public static final String QUIZ_ANSWERS_COLUMN_CORRECT = "correct";
    public static final String QUIZ_ANSWERS_COLUMN_QUIZ_QUESTION_ID = "question_id";

    public static final String QUIZ_QUESTIONS_COLUMN_ID = "id";
    public static final String QUIZ_QUESTIONS_COLUMN_CATEGORY = "category";
    public static final String QUIZ_QUESTIONS_COLUMN_SUBCATEGORY = "subcategory";
    public static final String QUIZ_QUESTIONS_COLUMN_DIFFICULTY = "difficulty";
    public static final String QUIZ_QUESTIONS_COLUMN_QUESTION = "question";

    // pivot table for quizzes and questions
    public static final String QUESTION_QUIZ_COLUMN_ID = "id";
    public static final String QUESTION_QUIZ_COLUMN_QUIZ_ID = "quiz_id";
    public static final String QUESTION_QUIZ_COLUMN_QUESTION_ID = "question_id";
    public static final String QUESTION_QUIZ_COLUMN_NUMBER = "question_number";

    public static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    USERS_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                    USERS_COLUMN_USERNAME + " VARCHAR(50) NOT NULL, " +
                    USERS_COLUMN_PASSWORD + " VARCHAR(50) NOT NULL" +
                    ");";

    public static final String CREATE_TABLE_QUIZZES =
            "CREATE TABLE " + TABLE_QUIZZES + " (" +
                    QUIZZES_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
//                    QUIZZES_COLUMN_AUTHOR + " INTEGER NOT NULL, " +
                    QUIZZES_COLUMN_TITLE + " VARCHAR(50) NOT NULL, " +
                    QUIZZES_COLUMN_DESCRIPTION + " TEXT" +
//                    " FOREIGN KEY (" + QUIZZES_COLUMN_AUTHOR + ") REFERENCES " + TABLE_USERS + " (" + USERS_COLUMN_ID + ") ON DELETE CASCADE" +
                    ");";

    public static final String CREATE_TABLE_QUIZ_ANSWERS =
            "CREATE TABLE " + TABLE_QUIZ_ANSWERS + " (" +
                    QUIZ_ANSWERS_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                    QUIZ_ANSWERS_COLUMN_ANSWER + " VARCHAR(255) NOT NULL, " +
                    QUIZ_ANSWERS_COLUMN_CORRECT + " INTEGER DEFAULT 0, " +
                    QUIZ_ANSWERS_COLUMN_QUIZ_QUESTION_ID + " INTEGER NOT NULL, " +
                    " FOREIGN KEY (" + QUIZ_ANSWERS_COLUMN_QUIZ_QUESTION_ID + ") REFERENCES " + TABLE_QUIZ_QUESTIONS + " (" + QUIZ_QUESTIONS_COLUMN_ID + ") ON DELETE CASCADE" +
                    ");";

    public static final String CREATE_TABLE_QUIZ_QUESTIONS =
            "CREATE TABLE " + TABLE_QUIZ_QUESTIONS + " (" +
                    QUIZ_QUESTIONS_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                    QUIZ_QUESTIONS_COLUMN_CATEGORY + " VARCHAR(55) NOT NULL, " +
                    QUIZ_QUESTIONS_COLUMN_SUBCATEGORY + " VARCHAR(55), " +
                    QUIZ_QUESTIONS_COLUMN_DIFFICULTY + " INTEGER DEFAULT 1, " +
                    QUIZ_QUESTIONS_COLUMN_QUESTION + " VARCHAR(255) NOT NULL" +
                    ");";

    public static final String CREATE_TABLE_QUESTION_QUIZ = "CREATE TABLE " + TABLE_QUESTION_QUIZ + " (" +
            QUESTION_QUIZ_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
            QUESTION_QUIZ_COLUMN_NUMBER + " INTEGER NOT NULL, " +
            QUESTION_QUIZ_COLUMN_QUIZ_ID + " INTEGER NOT NULL, " +
            QUESTION_QUIZ_COLUMN_QUESTION_ID + " INTEGER NOT NULL, " +
            " FOREIGN KEY (" + QUESTION_QUIZ_COLUMN_QUIZ_ID  + ") REFERENCES " + TABLE_QUIZZES + " (" + QUIZZES_COLUMN_ID + ") ON DELETE CASCADE, " +
            " FOREIGN KEY (" + QUESTION_QUIZ_COLUMN_QUESTION_ID  + ") REFERENCES " + TABLE_QUIZZES + " (" + QUIZZES_COLUMN_ID + ") ON DELETE CASCADE" +
            ");";
}
