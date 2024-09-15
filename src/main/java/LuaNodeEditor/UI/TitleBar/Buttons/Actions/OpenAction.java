package LuaNodeEditor.UI.TitleBar.Buttons.Actions;

import LuaNodeEditor.Logging.BaseLogger;
import LuaNodeEditor.LuaNodeEditor;
import LuaNodeEditor.UI.Components.FolderSelectionPopup;
import javafx.application.Platform;
import javafx.stage.Stage;

public class OpenAction {

    public static void handleOpenAction() {
        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();

                FolderSelectionPopup popup = new FolderSelectionPopup(false, stage, directory -> {
                    //LuaNodeEditor.contentBrowser.updateContent(directory);
                });

                popup.show();

            } catch (Exception pException) {
                BaseLogger.logError("Error while trying to open the OpenAction popup", pException);
            }
        });
    }
}
