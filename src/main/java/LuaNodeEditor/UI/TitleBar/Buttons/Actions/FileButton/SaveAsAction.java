package LuaNodeEditor.UI.TitleBar.Buttons.Actions.FileButton;

import LuaNodeEditor.Logging.BaseLogger;

public class SaveAsAction {

    public static void handleSaveAsAction() {
        System.out.println("Save as action called!");

        BaseLogger.logSuccess("Save as action called!");
    }
}
