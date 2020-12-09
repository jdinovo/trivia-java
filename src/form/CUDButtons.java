package form;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class CUDButtons extends Pane {
    private final Button createButton, updateButton, deleteButton;

    public CUDButtons() {
        createButton = new Button("New");
        updateButton = new Button("Edit");
        deleteButton = new Button("Delete");

        createButton.setStyle("-fx-base: rgb(0,130,0); -fx-text-fill: rgb(255,255,255);");
        updateButton.setStyle("-fx-base: rgb(200,100,0); -fx-text-fill: rgb(255,255,255);");
        deleteButton.setStyle("-fx-base: rgb(180,0,0); -fx-text-fill: rgb(255,255,255);");

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(createButton, updateButton, deleteButton);

        getChildren().addAll(hBox);
    }

    public Button getCreateButton() {
        return createButton;
    }

    public Button getUpdateButton() {
        return updateButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }
}
