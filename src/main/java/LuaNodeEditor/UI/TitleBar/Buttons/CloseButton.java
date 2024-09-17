package LuaNodeEditor.UI.TitleBar.Buttons;

import LuaNodeEditor.UI.Components.TextButton;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CloseButton extends TextButton {

    public CloseButton(Stage pStage) {
        super("x", TextButton.BUTTON_PANEL_COLOR, TextButton.HOVER_COLOR, 40, 30);

        setOnAction(event -> pStage.close());
    }
}
