package panes.quiz;

import javabean.QuestionResult;
import javabean.Quiz;
import javabean.QuizQuestion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Pair;
import main.Main;
import scenes.QuizScene;

import java.util.ArrayList;

import static main.Const.HEADER_FONT;

public class QuizResultsPane extends BorderPane {

    private TableView<QuestionResult> tableView;

    public QuizResultsPane(Quiz quiz, ArrayList<QuestionResult> results) {

        int correctCount = results.stream().mapToInt(result -> result.getQuestionAnswer().isCorrect() ? 1 : 0).sum();

        Label header = new Label("RESULTS");
        header.setFont(HEADER_FONT);
        Label correctLabel = new Label(correctCount + "/" + results.size());
        correctLabel.setFont(HEADER_FONT);

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
//        tableView.setFixedCellSize(25);
        tableView.setPrefHeight(600);

        TableColumn<QuestionResult, String> questionCol = new TableColumn<>("Question");
        questionCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuizQuestion().getText()));
        questionCol.setCellFactory(param -> new TableCell<QuestionResult, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    Text text = new Text(item);
                    text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(35));
                    setGraphic(text);
                }
            }
        });

        TableColumn<QuestionResult, String> correctCol = new TableColumn<>("Selected Answer");
        correctCol.setCellValueFactory(cellData -> {
            String formatted = (cellData.getValue().getQuestionAnswer().isCorrect() ? "Correct" : "Incorrect") + ": " + cellData.getValue().getQuestionAnswer().getText();
            return new SimpleStringProperty(formatted);
        });
        correctCol.setCellFactory(param -> new TableCell<QuestionResult, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    Text text = new Text(item);
                    text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(35));
                    setGraphic(text);
                }
            }
        });

        tableView.getColumns().addAll(questionCol, correctCol);

        setBottom(tableView);


    }
}
