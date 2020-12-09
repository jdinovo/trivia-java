package scenes;

import javafx.scene.Scene;
import main.Construct;
import panes.QuizPane;

public class QuizScene extends Scene {

    public QuizScene() {
        super(new QuizPane(), Construct.SCREEN_WIDTH, Construct.SCREEN_HEIGHT);
    }
}
