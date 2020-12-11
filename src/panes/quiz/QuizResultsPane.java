package panes.quiz;

import javabean.QuestionResult;
import javabean.Quiz;
import javabean.QuizQuestion;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Pair;
import main.Main;
import scenes.QuizScene;

import java.util.ArrayList;

public class QuizResultsPane extends BorderPane {

    private TableView<QuestionResult> tableView;

    public QuizResultsPane(Quiz quiz, ArrayList<QuestionResult> results) {

        int correctCount = results.stream().mapToInt(result -> result.getQuestionAnswer().isCorrect() ? 1 : 0).sum();

        Label header = new Label("RESULTS");
        Label correctLabel = new Label(correctCount + "/" + results.size());

        HBox headerBox = new HBox();
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setSpacing(10);
        headerBox.getChildren().addAll(header, correctLabel);

        setCenter(headerBox);

        // exit button
        Button quit = new Button("Exit to Home");
        quit.setOnAction(e -> {
            Main.setMainScene();
        });
        setLeft(quit);

        // retry button
        Button retry = new Button("Retry Quiz");
        final Quiz quizF = quiz;
        retry.setOnAction(e -> {
            Main.window.setScene(new QuizScene(quizF));
        });
        setRight(retry);

        tableView = new TableView<>();

        tableView.setItems(FXCollections.observableArrayList(results));

        tableView.setEditable(false);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setFixedCellSize(25);
        tableView.setPrefHeight(600);

        TableColumn<QuestionResult, String> questionCol = new TableColumn<>("Question");
        questionCol.setCellValueFactory(new PropertyValueFactory<>("quizQuestion"));

        TableColumn<QuestionResult, String> correctCol = new TableColumn<>("Selected Answer");
        correctCol.setCellValueFactory(new PropertyValueFactory<>("questionAnswer"));

        tableView.getColumns().addAll(questionCol, correctCol);

        System.out.println(results);

        setBottom(tableView);


    }
}
