package LuaNodeEditor.UI.TitleBar.Buttons;

import LuaNodeEditor.UI.Components.TextButton;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MaximizeButton extends TextButton {

    public MaximizeButton(Stage pStage) {
        super("+", TextButton.BUTTON_PANEL_COLOR, TextButton.HOVER_COLOR, 40, 30);

        setOnAction(event -> pStage.setMaximized(!pStage.isMaximized()));
    }
}
