package LuaNodeEditor.UI.TitleBar.Buttons;

import LuaNodeEditor.Logging.BaseLogger;
import LuaNodeEditor.UI.Components.TextButton;
import LuaNodeEditor.UI.TitleBar.Buttons.Actions.SettingsButton.SettingsWindow;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class SettingsButton extends TextButton {

    public SettingsButton(Stage pStage) {
        super("Settings", TextButton.NORMAL_COLOR, TextButton.HOVER_COLOR, 70, 30);
        setPadding(new Insets(5, 10, 5, 10));

        setOnAction(event -> new SettingsWindow(pStage));

        BaseLogger.logSuccess("Settings button created");
    }
}
