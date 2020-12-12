package panes;

import form.SEDButtons;
import javabean.QuestionResult;
import javabean.QuizQuestion;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.Main;
import scenes.QuizScene;
import tables.QuizQuestionTable;
import tabs.EditQuestionTab;
import tabs.EditQuizTab;

public class QuestionViewPane extends BorderPane {

    private static TableView<QuizQuestion> tableView = new TableView<>();;

    private static final QuizQuestionTable quizQuestionTable = new QuizQuestionTable();;

    private final SEDButtons sedButtons;

    private QuizQuestion quizQuestion;

    public QuestionViewPane() {

        quizQuestion = new QuizQuestion();

        tableView = new TableView<>();;
        sedButtons = new SEDButtons(false);
        buttonsVisible(false);

        refreshTable();

        tableView.setEditable(false);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//        tableView.setFixedCellSize(25);
        tableView.setPrefHeight(700);

        TableColumn<QuizQuestion, String> questionCol = new TableColumn<>("Question");
        questionCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        questionCol.setCellFactory(param -> new TableCell<QuizQuestion, String>() {
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

        TableColumn<QuizQuestion, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<QuizQuestion, String> difficultyCol = new TableColumn<>("Difficulty");
        difficultyCol.setCellValueFactory(new PropertyValueFactory<>("difficulty"));

        questionCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.7));
        categoryCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
        difficultyCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.098));

        questionCol.setResizable(false);
        categoryCol.setResizable(false);
        difficultyCol.setResizable(false);

        tableView.getColumns().addAll(questionCol, categoryCol, difficultyCol);

        tableView.setRowFactory(tv -> {
            TableRow<QuizQuestion> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    quizQuestion = row.getItem();

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

        sedButtons.getUpdateButton().setOnAction(e -> {
            EditQuestionTab editQuizQuestionTab = EditQuestionTab.getInstance(quizQuestion);
            //if tab is not already open
            if(!MainMenuPane.getTabPane().getTabs().contains(editQuizQuestionTab) && !MainMenuPane.getTabPane().getTabs().contains(EditQuestionTab.getInstance(quizQuestion))) {
                MainMenuPane.getTabPane().getTabs().add(editQuizQuestionTab);
                MainMenuPane.getTabPane().getSelectionModel().select(editQuizQuestionTab);
            } else if(MainMenuPane.getTabPane().getTabs().contains(EditQuestionTab.getInstance(quizQuestion))){
                MainMenuPane.getTabPane().getSelectionModel().select(EditQuestionTab.getInstance(quizQuestion));
            } else {
                MainMenuPane.getTabPane().getSelectionModel().select(editQuizQuestionTab);
            }
        });

        sedButtons.getDeleteButton().setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure?");
            alert.setGraphic(null);
            alert.setContentText("Are you sure you want to delete this question?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                quizQuestionTable.deleteQuizQuestion(quizQuestion);
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
        tableView.setItems(FXCollections.observableArrayList(quizQuestionTable.getAllQuizQuestions()));
        tableView.refresh();
    }
}
