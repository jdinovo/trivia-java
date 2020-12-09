package scenes;

import javabean.Quiz;
import javafx.scene.Scene;
import main.Construct;
import panes.QuizPane;

public class QuizScene extends Scene {

    public QuizScene(Quiz quiz) {
        super(new QuizPane(quiz), Construct.SCREEN_WIDTH, Construct.SCREEN_HEIGHT);
    }
}
