package form;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class SEDButtons extends HBox {
    private final Button startButton, updateButton, deleteButton;

    public SEDButtons(boolean includeStart) {

        startButton = new Button("Start");
        updateButton = new Button("Edit");
        deleteButton = new Button("Delete");

        startButton.setStyle("-fx-base: rgb(0,130,0); -fx-text-fill: rgb(255,255,255);");
        updateButton.setStyle("-fx-base: rgb(200,100,0); -fx-text-fill: rgb(255,255,255);");
        deleteButton.setStyle("-fx-base: rgb(180,0,0); -fx-text-fill: rgb(255,255,255);");

        startButton.setPrefSize(100, 50);
        updateButton.setPrefSize(100, 50);
        deleteButton.setPrefSize(100, 50);

        setAlignment(Pos.CENTER);
        setSpacing(10);
        setPadding(new Insets(10));
        if (includeStart) {
            getChildren().addAll(startButton, updateButton, deleteButton);
        } else {
            getChildren().addAll(updateButton, deleteButton);
        }

        setPrefSize(350, 70);
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getUpdateButton() {
        return updateButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }
}
