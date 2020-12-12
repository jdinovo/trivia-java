package form;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class SEDButtons extends ButtonGroup {

    public SEDButtons(boolean includeStart) {
        Button createButton = getCreateButton();
        Button updateButton = getUpdateButton();
        Button deleteButton = getDeleteButton();

        createButton.setText("Start");
        updateButton.setText("Edit");
        deleteButton.setText("Delete");

        createButton.setPrefSize(100, 50);
        updateButton.setPrefSize(100, 50);
        deleteButton.setPrefSize(100, 50);

        if (includeStart) {
            getChildren().addAll(createButton, updateButton, deleteButton);
        } else {
            getChildren().addAll(updateButton, deleteButton);
        }

        setPrefSize(350, 70);
    }
}
