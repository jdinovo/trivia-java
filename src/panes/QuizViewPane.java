package panes;

import form.SEDButtons;
import javabean.Quiz;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import main.Main;
import scenes.QuizScene;
import tables.QuizTable;
import tabs.EditQuizTab;
import tabs.NewQuizTab;

public class QuizViewPane extends BorderPane {

    private static TableView<Quiz> tableView = new TableView<>();;

    private static final QuizTable quizTable = new QuizTable();;

    private final SEDButtons sedButtons;

    private Quiz quiz;

    public QuizViewPane() {

        quiz = new Quiz();

        tableView = new TableView<>();;
        sedButtons = new SEDButtons(true);
        sedButtons.setVisible(false);

        refreshTable();

        tableView.setEditable(false);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setFixedCellSize(25);
        tableView.setPrefHeight(700);

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

                    if (!sedButtons.isVisible()) {
                        FadeTransition fade = new FadeTransition(Duration.millis(500), sedButtons);
                        fade.setFromValue(.1);
                        fade.setToValue(1);
                        fade.setCycleCount(1);
                        fade.setAutoReverse(false);
                        fade.play();
                        buttonsVisible(true);
                    }
                }
            });
            return row ;
        });

        sedButtons.getStartButton().setOnAction(e -> {
            Main.window.setScene(new QuizScene(quiz));
        });

        sedButtons.getUpdateButton().setOnAction(e -> {
            EditQuizTab editQuizTab = EditQuizTab.getInstance(quiz);
            //if tab is not already open
            if(!MainMenuPane.getTabPane().getTabs().contains(editQuizTab) && !MainMenuPane.getTabPane().getTabs().contains(EditQuizTab.getInstance(quiz))) {
                MainMenuPane.getTabPane().getTabs().add(editQuizTab);
                MainMenuPane.getTabPane().getSelectionModel().select(editQuizTab);
            } else if(MainMenuPane.getTabPane().getTabs().contains(EditQuizTab.getInstance(quiz))){
                MainMenuPane.getTabPane().getSelectionModel().select(EditQuizTab.getInstance(quiz));
            } else {
                MainMenuPane.getTabPane().getSelectionModel().select(editQuizTab);
            }
        });

        sedButtons.getDeleteButton().setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure?");
            alert.setGraphic(null);
            alert.setContentText("Are you sure you want to delete this quiz?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                quizTable.deleteQuiz(quiz);
                buttonsVisible(false);
                refreshTable();
            }
        });


        setTop(tableView);
        setBottom(sedButtons);

    }

    private void buttonsVisible(boolean visible) {
        if (visible) {
            tableView.setPrefHeight(630);
            sedButtons.setVisible(true);
        } else {
            tableView.setPrefHeight(700);
            sedButtons.setVisible(false);
        }
    }

    public static void refreshTable() {
        tableView.setItems(FXCollections.observableArrayList(quizTable.getAllQuizzes()));
        tableView.refresh();
    }

}
