package LuaNodeEditor.UI.TitleBar.Buttons;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ButtonPanel extends HBox {

    public ButtonPanel(Stage pStage) {

        MinimizeButton minimizeButton = new MinimizeButton(pStage);
        MaximizeButton maximizeButton = new MaximizeButton(pStage);
        CloseButton closeButton = new CloseButton(pStage);

        double buttonWidth = 40;
        double buttonHeight = 30;
        minimizeButton.setPrefSize(buttonWidth, buttonHeight);
        maximizeButton.setPrefSize(buttonWidth, buttonHeight);
        closeButton.setPrefSize(buttonWidth, buttonHeight);

        getChildren().addAll(minimizeButton, maximizeButton, closeButton);

        setSpacing(5);

        setPadding(new Insets(5, 10, 5, 10));
    }
}
