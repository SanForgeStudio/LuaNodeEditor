package LuaNodeEditor.UI.TitleBar.Buttons.ControlButtons;

import LuaNodeEditor.UI.Components.TextButton;
import javafx.stage.Stage;

public class MaximizeButton extends TextButton {

    public MaximizeButton(Stage pStage) {
        super("+", TextButton.BUTTON_PANEL_COLOR, TextButton.HOVER_COLOR, 40, 30);

        setOnAction(event -> pStage.setMaximized(!pStage.isMaximized()));
    }
}
