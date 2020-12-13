package panes;

import form.QuestionSelectionList;
import form.QuizCUForm;
import javabean.QuestionQuiz;
import javabean.Quiz;
import javabean.QuizQuestion;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import tables.QuestionQuizRelationTable;
import tables.QuizQuestionTable;
import tables.QuizTable;
import tabs.EditQuizTab;
import tabs.NewQuizTab;

import java.util.ArrayList;
import java.util.HashSet;


public class QuizCUPane extends BorderPane {

    // access tables
    private QuizTable quizTable;
    private QuizQuestionTable quizQuestionTable;
    private QuestionQuizRelationTable questionQuizRelationTable;

    // information
    HashSet<QuestionQuiz> questionQuizzesToDelete;

    // gui items
    private QuestionSelectionList questionSelectionList;
    private QuestionSelectionList selectedQuestionList;
    private final QuizCUForm form;

    // alert
    Alert alertSuccess;

    public QuizCUPane() {

        // gui
        form = new QuizCUForm(false);

        generalLayout();

        selectedQuestionList.getAudButtons().getDeleteButton().setOnAction(e -> {
            QuizQuestion q = selectedQuestionList.getSelectedQuestion();
            selectedQuestionList.removeQuestion(q);
            questionSelectionList.addQuestion(q);
        });

        form.getCreateButton().setOnAction(e -> {
            String title = form.getTitleField().getText().trim();
            String description = form.getdescriptionArea().getText().trim();

            if (!title.isEmpty() && !description.isEmpty()) {
                int quizId = quizTable.createQuiz(new Quiz(title, description));

                ArrayList<QuizQuestion> questions = selectedQuestionList.getQuizQuestions();

                questions.forEach(qu -> {
                    questionQuizRelationTable.createQuestionQuiz(new QuestionQuiz(quizId, qu.getId()));
                });

                QuizViewPane.refreshTable();
                NewQuizTab.closeInstance();

                alertSuccess.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Data");
                alert.setContentText("Ensure all fields are filled!");
                alert.show();
            }

        });

    }

    public QuizCUPane(Quiz quiz) {

        // gui
        form = new QuizCUForm(true);

        generalLayout();

        form.getTitleField().setText(quiz.getTitle());
        form.getdescriptionArea().setText(quiz.getDescription());

        ArrayList<QuizQuestion> selectedQuestions = quizQuestionTable.getQuestionsForQuiz(quiz.getId());
        selectedQuestionList.getQuizQuestions().clear();
        selectedQuestionList.getQuizQuestions().addAll(selectedQuestions);
        selectedQuestionList.refreshList();

        selectedQuestions.forEach(qu -> {
            questionSelectionList.removeQuestion(qu);
        });
        questionSelectionList.refreshList();

        selectedQuestionList.getAudButtons().getDeleteButton().setOnAction(e -> {
            QuizQuestion q = selectedQuestionList.getSelectedQuestion();
            selectedQuestionList.removeQuestion(q);
            questionSelectionList.addQuestion(q);
            removeQuestion(quiz, q);
        });



        form.getCreateButton().setOnAction(e -> {
            String title = form.getTitleField().getText().trim();
            String description = form.getdescriptionArea().getText().trim();

            if (!title.isEmpty() && !description.isEmpty()) {
                quiz.setTitle(title);
                quiz.setDescription(description);
                quizTable.updateQuiz(quiz);
                ArrayList<QuizQuestion> questions = selectedQuestionList.getQuizQuestions();

                questionQuizzesToDelete.forEach(qq -> {
                    questionQuizRelationTable.deleteQuestionQuiz(qq);
                });

                questions.forEach(qu -> {
                    questionQuizRelationTable.createQuestionQuiz(new QuestionQuiz(quiz.getId(), qu.getId()));
                });

                QuizViewPane.refreshTable();
                EditQuizTab.closeInstance();

                alertSuccess.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Data");
                alert.setContentText("Ensure all fields are filled!");
                alert.show();
            }

        });
    }

    private void generalLayout() {
        // alert
        // alert
        alertSuccess = new Alert(Alert.AlertType.INFORMATION);
        alertSuccess.setTitle("Success");
        alertSuccess.setHeaderText("Quiz Data Saved");
        alertSuccess.setContentText("The quiz data was saved successfully!");

        // db access
        quizTable = new QuizTable();
        quizQuestionTable = new QuizQuestionTable();
        questionQuizRelationTable = new QuestionQuizRelationTable();

        questionQuizzesToDelete = new HashSet<>();

        // gui
        questionSelectionList = new QuestionSelectionList("Available Questions",false);
        selectedQuestionList = new QuestionSelectionList("Selected Questions",true);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(form, selectedQuestionList, questionSelectionList);
        setCenter(hBox);
        setAlignment(hBox, Pos.CENTER);

        questionSelectionList.getAudButtons().getCreateButton().setOnAction(e -> {
            QuizQuestion q = questionSelectionList.getSelectedQuestion();
            selectedQuestionList.addQuestion(q);
            questionSelectionList.removeQuestion(q);
        });

    }

    private void removeQuestion(Quiz q, QuizQuestion quizQuestion) {
        QuestionQuiz qq = questionQuizRelationTable.getQuestionQuiz(q.getId(), quizQuestion.getId());
        if (qq.getId() > 0) {
            questionQuizzesToDelete.add(qq);
        }
    }

}
