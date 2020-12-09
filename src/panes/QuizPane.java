package panes;

import javabean.Quiz;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import main.Main;

public class QuizPane extends Pane {
    public QuizPane(Quiz quiz) {
        Button button = new Button("Click");

        button.setOnAction(e -> {
            Main.setMainScene();
        });

        getChildren().addAll(button);
    }
}
