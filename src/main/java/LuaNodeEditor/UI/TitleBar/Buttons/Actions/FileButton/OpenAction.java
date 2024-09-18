package LuaNodeEditor.UI.TitleBar.Buttons.Actions.FileButton;

import LuaNodeEditor.Logging.BaseLogger;
import LuaNodeEditor.LuaNodeEditor;
import LuaNodeEditor.UI.Components.FolderSelectionPopup;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.File;

public class OpenAction {

    public static void handleOpenAction() {
        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();

                BaseLogger.logSuccess("Opened the OpenAction popup");

                FolderSelectionPopup popup = new FolderSelectionPopup(false, stage, pDirectory -> {
                    BaseLogger.logInfo("Selected directory: " + pDirectory.getAbsolutePath());
                    LuaNodeEditor.contentBrowser.updateContent(new File(pDirectory.getAbsolutePath()));
                });

                popup.show();

            } catch (Exception pException) {
                BaseLogger.logError("Error while trying to open the OpenAction popup", pException);
            }
        });
    }
}
