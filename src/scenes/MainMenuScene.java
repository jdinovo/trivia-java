package scenes;

import javafx.scene.Scene;
import main.Construct;
import panes.MainMenuPane;

public class MainMenuScene extends Scene {

    public MainMenuScene() {
        super(new MainMenuPane(), Construct.SCREEN_WIDTH, Construct.SCREEN_HEIGHT);
    }
}
