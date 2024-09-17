package LuaNodeEditor.UI.TitleBar.Buttons.ControlButtons;

import LuaNodeEditor.UI.Components.TextButton;
import javafx.stage.Stage;

public class CloseButton extends TextButton {

    public CloseButton(Stage pStage) {
        super("x", TextButton.BUTTON_PANEL_COLOR, TextButton.HOVER_COLOR, 40, 30);

        setOnAction(event -> pStage.close());
    }
}
