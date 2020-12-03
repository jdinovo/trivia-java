package database;

import java.sql.*;


/**
 *
 * The Database class is a singleton class that represents our database connection
 *
 */
public class Database {
    //create private instance variable
    private static Database instance = null;
    private Connection connection = null;
    //create private constructor
    private Database() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:trivia.db");
            System.out.println("Successfully Created Connection");
        } catch (Exception e) {
            e.printStackTrace();

        }
        try {
            createTable(DBConst.TABLE_USERS, DBConst.CREATE_TABLE_USERS, connection);
            createTable(DBConst.TABLE_QUIZZES, DBConst.CREATE_TABLE_QUIZZES, connection);
            createTable(DBConst.TABLE_QUIZ_ANSWERS, DBConst.CREATE_TABLE_QUIZ_ANSWERS, connection);
            createTable(DBConst.TABLE_QUIZ_QUESTIONS, DBConst.CREATE_TABLE_QUIZ_QUESTIONS, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * gets the single instance of the database connection
     * if there isnt an instance, it will create a new one
     *
     * @return instance
     *
     */
    public static Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     *
     * gets the database connection
     *
     * @return connection
     *
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     *
     * Will create the table if it does not exist in the db
     * @param tableName is the name of the table to be created in the db
     * @param tableQuery is the query needed to create the table in the db
     * @param connection is the connection to the db
     * @throws SQLException if table creation fails
     */
    private void createTable(String tableName, String tableQuery, Connection connection) throws SQLException {
        Statement sqlStatement;
        //Grab the database meta data
        DatabaseMetaData md = connection.getMetaData();
        //Grab if the table exists in the database
        ResultSet result = md.getTables(null, null, tableName, null);
        //if I have a next value (the table exists, otherwise it does not)
        if(result.next()) {
            System.out.println( tableName + " Table already exists");
        } else {
            sqlStatement = connection.createStatement();
            sqlStatement.execute(tableQuery);
            System.out.println("The " + tableName + " table has been inserted");
        }
    }
}
