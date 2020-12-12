package form;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public abstract class ButtonGroup extends HBox {
    private final Button createButton, updateButton, deleteButton;

    public ButtonGroup() {

        createButton = new Button();
        updateButton = new Button();
        deleteButton = new Button();

        createButton.setStyle("-fx-base: rgb(0,130,0); -fx-text-fill: rgb(255,255,255);");
        updateButton.setStyle("-fx-base: rgb(200,100,0); -fx-text-fill: rgb(255,255,255);");
        deleteButton.setStyle("-fx-base: rgb(180,0,0); -fx-text-fill: rgb(255,255,255);");

        setAlignment(Pos.CENTER);
        setSpacing(10);
        setPadding(new Insets(10));

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
