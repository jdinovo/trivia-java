package panes;

import javabean.Quiz;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import tables.QuizTable;

public class QuizViewPane extends BorderPane {

    private static TableView<Quiz> tableView = new TableView<>();;

    private static final QuizTable quizTable = new QuizTable();;

    private Quiz quiz;

    public QuizViewPane() {

        quiz = new Quiz();


        tableView = new TableView<>();;
        refreshTable();

        tableView.setEditable(false);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setFixedCellSize(25);
        tableView.setPrefHeight(675);

        TableColumn<Quiz, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Quiz, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableView.getColumns().addAll(titleCol, descriptionCol);

        tableView.setRowFactory(tv -> {
            TableRow<Quiz> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    quiz = row.getItem();

                    tableView.setPrefHeight(150);


//                    FadeTransition fade = new FadeTransition(Duration.millis(500), hBox);
//                    fade.setFromValue(.1);
//                    fade.setToValue(1);
//                    fade.setCycleCount(1);
//                    fade.setAutoReverse(false);
//                    fade.play();
                }
            });
            return row ;
        });

        setTop(tableView);

    }

    public static void refreshTable() {
        tableView.setItems(FXCollections.observableArrayList(quizTable.getAllQuizzes()));
        tableView.refresh();
    }

}
