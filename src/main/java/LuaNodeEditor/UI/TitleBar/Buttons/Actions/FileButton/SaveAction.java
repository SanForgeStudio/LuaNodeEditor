package LuaNodeEditor.UI.TitleBar.Buttons.Actions.FileButton;

import LuaNodeEditor.Logging.BaseLogger;

public class SaveAction {

    public static void handleSaveAction() {
        System.out.println("Save action");

        BaseLogger.logSuccess("Save action called!");
    }
}
