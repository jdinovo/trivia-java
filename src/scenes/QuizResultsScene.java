package scenes;

import javabean.QuestionResult;
import javabean.Quiz;
import javabean.QuizQuestion;
import javafx.scene.Scene;
import javafx.util.Pair;
import main.Construct;
import panes.quiz.QuizResultsPane;

import java.util.ArrayList;

public class QuizResultsScene extends Scene {
    public QuizResultsScene(Quiz quiz, ArrayList<QuestionResult> results) {
        super(new QuizResultsPane(quiz, results), Construct.SCREEN_WIDTH, Construct.SCREEN_HEIGHT);
    }
}
