package database;

import javabean.Difficulty;
import javabean.QuestionAnswer;
import javabean.QuizQuestion;
import javafx.scene.control.Alert;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import panes.QuestionViewPane;
import tables.QuestionAnswerTable;
import tables.QuizQuestionTable;

import java.io.*;
import java.util.Iterator;

public class FileProcessor {

    private JSONParser parser;
    private QuizQuestionTable quizQuestionTable;
    private QuestionAnswerTable questionAnswerTable;

    public FileProcessor() {
        parser = new JSONParser();
        quizQuestionTable = new QuizQuestionTable();
        questionAnswerTable = new QuestionAnswerTable();

    }

    /**
     * Reads question JSON file into database
     * @param filePath
     */
    public void readQuestionsIntoDB(String filePath) {
        try {
            Reader reader = new FileReader(filePath);

            JSONArray questions = (JSONArray) parser.parse(reader);

            Iterator<JSONObject> qIterator = questions.iterator();

            // iterate over questions
            while (qIterator.hasNext()) {
                // extract object
                JSONObject object = qIterator.next();

                // get category and sub category
                String[] catArray = object.get("category").toString().split(":");

                // get difficulty
                Difficulty difficulty;
                switch (object.get("difficulty").toString()) {
                    case "hard":
                        difficulty = Difficulty.HARD;
                        break;
                    case "easy":
                        difficulty = Difficulty.EASY;
                        break;
                    default:
                        difficulty = Difficulty.NORMAL;
                }

                // get question text
                String questionText = object.get("question").toString();

                // create question
                QuizQuestion question = new QuizQuestion(catArray[0], (catArray.length > 1 ? catArray[1].trim() : ""), difficulty, questionText);

                // store question in DB and get id
                question.setId(quizQuestionTable.createQuizQuestion(question));

                // create correct answer
                String correctAnswer = object.get("correct_answer").toString();
                questionAnswerTable.createQuestionAnswer(new QuestionAnswer(correctAnswer, true, question));

                // create incorrect answers
                JSONArray incorrectAnswerJSON = (JSONArray) object.get("incorrect_answers");
                for (Object answerObject : incorrectAnswerJSON) {
                    questionAnswerTable.createQuestionAnswer(new QuestionAnswer(answerObject.toString(), false, question));
                }

            }

            reader.close();

            // refresh table
            QuestionViewPane.refreshTable();

        } catch (ParseException | IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error Importing JSON");
            alert.setContentText("Ensure the file path is correct and the JSON data is not malformed!");
            alert.show();
        }
    }


}
