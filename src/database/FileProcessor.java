package database;

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
import java.util.ArrayList;
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

                // create question
                QuizQuestion question = QuizQuestion.jsonToQuizQuestion(object);

                // store question in DB and get id
                question.setId(quizQuestionTable.createQuizQuestion(question));

                // create answers
                JSONArray incorrectAnswerJSON = (JSONArray) object.get("answers");
                Iterator<JSONObject> answerIterator = incorrectAnswerJSON.iterator();
                while (answerIterator.hasNext()){
                    JSONObject ansObj = answerIterator.next();
                    questionAnswerTable.createQuestionAnswer(QuestionAnswer.jsonToQuestionAnswer(ansObj, question));
                }

            }

            reader.close();

            // refresh table
            QuestionViewPane.refreshTable();

            // alert
            Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
            alertSuccess.setTitle("Success");
            alertSuccess.setHeaderText("Quiz Questions Imported");
            alertSuccess.setContentText("The quiz questions were successfully imported!");
            alertSuccess.show();

        } catch (ParseException | IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error Importing JSON");
            alert.setContentText("Ensure the file path is correct and the JSON data is not malformed!");
            alert.show();
        }
    }

    public void exportQuestionsToFile(ArrayList<QuizQuestion> quizQuestions, String filename) {

        JSONArray questionArray = new JSONArray();

        for(QuizQuestion question : quizQuestions) {
            JSONObject qObject = QuizQuestion.quizQuestionToJson(question);

            QuestionAnswer[] answers = question.getAnswers(false);
            JSONArray answerArray = new JSONArray();

            for (QuestionAnswer answer : answers) {
                answerArray.add(QuestionAnswer.questionAnswerToJson(answer));
            }

            qObject.put("answers", answerArray);

            questionArray.add(qObject);
        }

        try (FileWriter file = new FileWriter(filename)) {

            file.write(questionArray.toJSONString());
            file.flush();

            // alert
            Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
            alertSuccess.setTitle("Success");
            alertSuccess.setHeaderText("Quiz Questions Exported");
            alertSuccess.setContentText("The quiz questions were successfully exported to `" + filename + "`!");
            alertSuccess.show();

        } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error Exporting JSON");
            alert.setContentText("There was an error exporting the quiz questions to JSON!");
            alert.show();
        }
    }


}
