package LuaNodeEditor.UI.TitleBar.Buttons.Actions.FileButton;

import LuaNodeEditor.Logging.BaseLogger;
import LuaNodeEditor.LuaNodeEditor;
import LuaNodeEditor.UI.Components.FolderSelectionPopup;
import javafx.application.Platform;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class NewAction {

    public static void handleNewAction() {
        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                showFolderSelectionPopup(stage);
            } catch (Exception pException) {
                BaseLogger.logError("Error while trying to open the NewAction popup", pException);
            }
        });
    }

    private static void showFolderSelectionPopup(Stage pStage) {
        FolderSelectionPopup popup = new FolderSelectionPopup(false, pStage, pDirectory -> {
            BaseLogger.logInfo("Selected directory: " + pDirectory.getAbsolutePath());
            createNewFolder(pDirectory);
        });

        popup.show();
    }

    private static void createNewFolder(File pDirectory) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create New Folder");
        dialog.setHeaderText("Create a New Folder");
        dialog.setContentText("Enter folder name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(pFolderName -> {
            File newFolder = new File(pDirectory, pFolderName);
            if (newFolder.exists()) {
                BaseLogger.logWarning("Folder already exists.");
            } else if (newFolder.mkdir()) {
                BaseLogger.logSuccess("Folder created: " + newFolder.getAbsolutePath());
                LuaNodeEditor.contentBrowser.updateContent(newFolder);
            } else {
                BaseLogger.logError("Failed to create folder.", null);
            }
        });
    }
}
