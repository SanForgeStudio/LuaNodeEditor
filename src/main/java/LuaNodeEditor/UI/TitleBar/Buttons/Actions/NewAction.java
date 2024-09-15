package LuaNodeEditor.UI.TitleBar.Buttons.Actions;

import LuaNodeEditor.Logging.BaseLogger;
import LuaNodeEditor.UI.Components.FolderSelectionPopup;
import javafx.application.Platform;
import javafx.stage.Stage;

public class NewAction {

    public static void handleNewAction() {
        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();

                /** TODO: Change to a way to create a new project
                FolderSelectionPopup popup = new FolderSelectionPopup(true, stage, directory -> {
                    System.out.println("Selected directory: " + directory.getAbsolutePath());
                });

                 popup.show();
                 */

            } catch (Exception pException) {
                BaseLogger.logError("Error while trying to open the NewAction popup", pException);
            }
        });
    }
}
