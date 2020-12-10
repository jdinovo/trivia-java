package panes;

import form.AnswerCUForm;
import form.AUDButtons;
import form.QuestionCUForm;
import javabean.Difficulty;
import javabean.QuizAnswer;
import javabean.QuizQuestion;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tables.QuizAnswerTable;
import tables.QuizQuestionTable;
import tabs.EditQuestionTab;
import tabs.NewQuestionTab;

import java.util.ArrayList;

public class QuestionCUPane extends BorderPane {

    // db access
    private QuizQuestionTable quizQuestionTable;
    private QuizAnswerTable quizAnswerTable;

    // gui
    private QuestionCUForm questionCUForm;
    private AnswerCUForm answerCUForm;
    private ListView<QuizAnswer> answerListView;
    private AUDButtons audButtons;

    // items
    private ArrayList<QuizAnswer> answers;
    private QuizAnswer answer;

    // boxes
    private HBox centeredBox;
    private VBox answerListBox;

    public QuestionCUPane() {
        generalLayout();

        // gui
        questionCUForm = new QuestionCUForm(false);

        centeredBox.getChildren().addAll(questionCUForm, answerListBox);

        questionCUForm.getCreateButton().setOnAction(e -> {
            String questionText = questionCUForm.getQuestionArea().getText().trim();
            String categoryText = questionCUForm.getComboCategory().getValue();
            String subCategoryText = questionCUForm.getComboSubcategory().getValue();
            Difficulty difficulty = questionCUForm.getComboDifficulty().getValue();

            if (!questionText.isEmpty() && !categoryText.isEmpty() && answers.size() == 4) {
                int quizId = quizQuestionTable.createQuizQuestion(new QuizQuestion(categoryText, subCategoryText, difficulty, questionText));
                answers.forEach(answer -> {
                    answer.setQuizQuestion(new QuizQuestion(quizId, categoryText, subCategoryText, difficulty, questionText));
                    quizAnswerTable.createQuizAnswer(answer);
                });
                QuestionViewPane.refreshTable();
                NewQuestionTab.closeInstance();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Data");
                alert.setContentText("Ensure all fields are filled and there are 4 answers!");
                alert.show();
            }
        });

    }

    public QuestionCUPane(QuizQuestion quizQuestion) {
        generalLayout();

        // gui
        questionCUForm = new QuestionCUForm(true);

        answers = quizAnswerTable.getQuizAnswersForQuestion(quizQuestion.getId());
        updateListView();

        questionCUForm.getComboCategory().setValue(quizQuestion.getCategory());
        questionCUForm.getComboSubcategory().setValue(quizQuestion.getSubcategory());
        questionCUForm.getComboDifficulty().setValue(quizQuestion.getDifficulty());
        questionCUForm.getQuestionArea().setText(quizQuestion.getText());

        centeredBox.getChildren().addAll(questionCUForm, answerListBox);

        questionCUForm.getCreateButton().setOnAction(e -> {
            String questionText = questionCUForm.getQuestionArea().getText().trim();
            String categoryText = questionCUForm.getComboCategory().getValue();
            String subCategoryText = questionCUForm.getComboSubcategory().getValue();
            Difficulty difficulty = questionCUForm.getComboDifficulty().getValue();

            if (!questionText.isEmpty() && !categoryText.isEmpty() && answers.size() == 4) {
                quizQuestion.setText(questionText);
                quizQuestion.setCategory(categoryText);
                quizQuestion.setSubcategory(subCategoryText);
                quizQuestion.setDifficulty(difficulty);
                quizQuestionTable.updateQuizQuestion(quizQuestion);
                answers.forEach(ans -> {
                    if (ans.getId() > 0) {
                        quizAnswerTable.updateQuizAnswer(ans);
                    } else {
                        ans.setQuizQuestion(quizQuestion);
                        quizAnswerTable.createQuizAnswer(ans);
                    }
                });
                QuestionViewPane.refreshTable();
                EditQuestionTab.closeInstance();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Data");
                alert.setContentText("Ensure all fields are filled and there are 4 answers!");
                alert.show();
            }
        });

    }

    private void generalLayout() {
        answer = new QuizAnswer();

        // db access
        quizAnswerTable = new QuizAnswerTable();
        quizQuestionTable = new QuizQuestionTable();

        // gui
        Label answerListLabel = new Label("Four(4) Answers");
        audButtons = new AUDButtons();
        cudEditDeleteVisibility(false);

        answers = new ArrayList<>();

        answerListView = new ListView<>();
        updateListView();
        answerListView.setMaxSize(200, 125);

        answerListBox = new VBox();
        answerListBox.setAlignment(Pos.CENTER);
        answerListBox.getChildren().addAll(answerListLabel, answerListView, audButtons);

        centeredBox = new HBox();
        centeredBox.setAlignment(Pos.CENTER);
        centeredBox.setSpacing(10);
        setCenter(centeredBox);

        answerListView.setOnMousePressed(e -> {
            answer = answerListView.getSelectionModel().getSelectedItem();
            if (answer != null) {
                cudEditDeleteVisibility(true);
            }
        });

        audButtons.getAddButton().setOnAction(e -> {
            answerCUForm = new AnswerCUForm(false);
            swapAnswerViews(false);

            answerCUForm.getAddButton().setOnAction(f -> {
                String ansText = answerCUForm.getAnswerArea().getText().trim();
                boolean ansCorrect = answerCUForm.getCorrectCheckBox().isSelected();

                if (!ansText.isEmpty()) {
                    if (answers.size() < 4) {
                        answers.add(new QuizAnswer(ansText, ansCorrect));
                    }
                    updateListView();
                    swapAnswerViews(true);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Data");
                    alert.setContentText("Ensure all fields are filled!");
                    alert.show();
                }
            });

        });

        audButtons.getUpdateButton().setOnAction(e -> {
            answerCUForm = new AnswerCUForm(true);
            swapAnswerViews(false);
            answerCUForm.getAnswerArea().setText(answer.getText());
            answerCUForm.getCorrectCheckBox().selectedProperty().setValue(answer.isCorrect());

            answerCUForm.getAddButton().setOnAction(f -> {
                String ansText = answerCUForm.getAnswerArea().getText().trim();
                boolean ansCorrect = answerCUForm.getCorrectCheckBox().isSelected();

                if (!ansText.isEmpty()) {
                    answers.remove(answer);
                    answer.setCorrect(ansCorrect);
                    answer.setText(ansText);
                    answers.add(answer);
                    updateListView();
                    cudEditDeleteVisibility(false);
                    swapAnswerViews(true);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Data");
                    alert.setContentText("Ensure all fields are filled!");
                    alert.show();
                }
            });
        });

        audButtons.getDeleteButton().setOnAction(e -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure?");
            alert.setGraphic(null);
            alert.setContentText("Are you sure you want to delete this answer?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                quizAnswerTable.deleteQuizAnswer(answer);
                answers.remove(answer);
                cudEditDeleteVisibility(false);
                updateListView();
            }


        });


    }

    private void cudEditDeleteVisibility(boolean visible) {
        audButtons.getUpdateButton().setVisible(visible);
        audButtons.getDeleteButton().setVisible(visible);
    }

    private void cudAddVisibility(boolean visible) {
        audButtons.getAddButton().setVisible(visible);
    }

    private void swapAnswerViews(boolean showList) {
        if (showList) {
            centeredBox.getChildren().add(answerListBox);
            centeredBox.getChildren().remove(answerCUForm);
        } else {
            centeredBox.getChildren().remove(answerListBox);
            centeredBox.getChildren().add(answerCUForm);
        }

    }

    private void updateListView() {
        if (answers.size() >= 4) {
            cudAddVisibility(false);
        } else {
            cudAddVisibility(true);
        }
        answerListView.setItems(FXCollections.observableArrayList(answers));
        answerListView.refresh();
    }
}
