package main;

import javafx.application.Application;
import javafx.stage.Stage;
import scenes.MainMenuScene;

public class Main extends Application{
    public static Stage window;


    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setScene(new MainMenuScene());
        window.setTitle("Trivia");
        window.setMinWidth(1024);
        window.setMinHeight(768);
        window.setResizable(true);
        window.centerOnScreen();
        window.show();
    }
}
