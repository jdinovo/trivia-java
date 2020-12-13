package scenes;

import javabean.QuestionResult;
import javabean.Quiz;
import javafx.scene.Scene;
import main.Construct;
import panes.quiz.QuizResultsPane;

import java.util.ArrayList;

public class QuizResultsScene extends Scene {
    public QuizResultsScene(Quiz quiz, ArrayList<QuestionResult> results) {
        super(new QuizResultsPane(quiz, results), Construct.SCREEN_WIDTH, Construct.SCREEN_HEIGHT);
    }
}
