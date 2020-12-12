package panes;

import form.SEDButtons;
import javabean.Quiz;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.Main;
import scenes.QuizScene;
import tables.QuizTable;
import tabs.EditQuizTab;

public class QuizViewPane extends BorderPane {

    private static TableView<Quiz> tableView = new TableView<>();;

    private static final QuizTable quizTable = new QuizTable();;

    private final SEDButtons sedButtons;

    private Quiz quiz;

    public QuizViewPane() {

        quiz = new Quiz();

        tableView = new TableView<>();;
        sedButtons = new SEDButtons(true);
        buttonsVisible(false);

        refreshTable();

        tableView.setEditable(false);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//        tableView.setFixedCellSize(25);
        tableView.setPrefHeight(700);

        TableColumn<Quiz, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.setCellFactory(param -> new TableCell<Quiz, String>() {
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

        TableColumn<Quiz, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionCol.setCellFactory(param -> new TableCell<Quiz, String>() {
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

        sedButtons.getCreateButton().setOnAction(e -> {
            if (quiz.getQuestions(false).size() > 0) {
                Main.window.setScene(new QuizScene(quiz));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No Questions");
                alert.setHeaderText("This quiz has no questions!");
                alert.setGraphic(null);
                alert.setContentText("Add questions to the quiz and try again.");
                alert.show();
            }
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
                // close any edit tabs open since they might be editing this quiz
                EditQuizTab.closeInstance();
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
