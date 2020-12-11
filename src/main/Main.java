package main;

import javabean.User;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scenes.MainMenuScene;

public class Main extends Application{
    public static Stage window;
    private static Scene mainScene;

    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        MainMenuScene mainMenuScene = new MainMenuScene();
        mainScene = mainMenuScene;

        window = primaryStage;
        window.setScene(mainMenuScene);
        window.setTitle("Trivia");
        window.setResizable(false);
        window.centerOnScreen();
        window.show();
    }

    public static void setMainScene() {
        window.setScene(mainScene);
    }
}
