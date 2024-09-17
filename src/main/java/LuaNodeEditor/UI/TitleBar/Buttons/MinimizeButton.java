package LuaNodeEditor.UI.TitleBar.Buttons;

import LuaNodeEditor.UI.Components.TextButton;
import javafx.stage.Stage;

public class MinimizeButton extends TextButton {

    public MinimizeButton(Stage pStage) {
        super("-", TextButton.NORMAL_COLOR, TextButton.HOVER_COLOR, 40, 30);

        setOnAction(event -> pStage.setIconified(true));
    }
}
