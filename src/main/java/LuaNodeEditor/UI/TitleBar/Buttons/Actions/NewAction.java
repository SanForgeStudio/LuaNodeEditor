package LuaNodeEditor.UI.TitleBar.Buttons.Actions;

import LuaNodeEditor.Logging.BaseLogger;
import LuaNodeEditor.UI.Components.FolderSelectionPopup;
import LuaNodeEditor.UI.ContentBrowser.ContentBrowser;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.File;

public class NewAction {

    public static void handleNewAction() {
        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();

                FolderSelectionPopup popup = new FolderSelectionPopup(true, stage, directory -> {
                    System.out.println("Selected directory: " + directory.getAbsolutePath());
                    ContentBrowser.updateContent(new File(directory.getAbsolutePath()));
                });

                popup.show();


            } catch (Exception pException) {
                BaseLogger.logError("Error while trying to open the NewAction popup", pException);
            }
        });
    }
}
