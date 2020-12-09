package panes;

import form.CategoryChoice;
import form.QuestionCUForm;
import form.QuizCUForm;
import javabean.Difficulty;
import javabean.QuizQuestion;
import javafx.scene.layout.BorderPane;
import tables.QuestionQuizRelationTable;
import tables.QuizAnswerTable;
import tables.QuizQuestionTable;

import java.util.List;
import java.util.Map;

public class QuestionCUPane extends BorderPane {



    // db access
    private final QuizQuestionTable quizQuestionTable;
    private QuizAnswerTable quizAnswerTable;

    // gui
    private final QuestionCUForm form;

    public QuestionCUPane() {

        // db access
        quizAnswerTable = new QuizAnswerTable();
        quizQuestionTable = new QuizQuestionTable();

        // info


        // gui
        form = new QuestionCUForm("Create");

        setCenter(form);


        form.getCreateButton().setOnAction(e -> {
            String questionText = form.getQuestionArea().getText().trim();
            String categoryText = form.getComboCategory().getValue();
            String subCategoryText = form.getComboSubcategory().getValue();
            Difficulty difficulty = form.getComboDifficulty().getValue();

            if (!questionText.isEmpty() && !categoryText.isEmpty()) {
                quizQuestionTable.createQuizQuestion(new QuizQuestion(categoryText, subCategoryText, difficulty, questionText));
            }
        });



    }
}
