package LuaNodeEditor.UI.Components;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class FolderSelectionPopup {

    public interface DirectorySelectedCallback {
        void onDirectorySelected(File directory);
    }

    private final Stage primaryStage;
    private final DirectorySelectedCallback callback;
    private final boolean checkIfEmpty;

    public FolderSelectionPopup(boolean pCheckIfEmpty, Stage pPrimaryStage, DirectorySelectedCallback pCallback) {
        this.primaryStage = pPrimaryStage;
        this.callback = pCallback;
        this.checkIfEmpty = pCheckIfEmpty;
    }

    public void show() {
        Platform.runLater(() -> {
            while (true) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Choose Folder");
                directoryChooser.setInitialDirectory(getDownloadsFolder());

                File selectedDirectory = directoryChooser.showDialog(primaryStage);

                if (selectedDirectory == null) {
                    break;
                }

                if (selectedDirectory.isDirectory()) {
                    if (!checkIfEmpty || isDirectoryEmpty(selectedDirectory)) {
                        callback.onDirectorySelected(selectedDirectory);
                        break;
                    } else {
                        showWarningAlert("Selected folder is not empty. Please select an empty folder.");
                    }
                } else {
                    showWarningAlert("Selected path is not a folder. Please select a valid folder.");
                }
            }
        });
    }

    private File getDownloadsFolder() {
        String userHome = System.getProperty("user.home");
        File downloadsFolder = new File(userHome, "Downloads");
        if (downloadsFolder.exists() && downloadsFolder.isDirectory()) {
            return downloadsFolder;
        } else {
            return new File(userHome);
        }
    }

    private boolean isDirectoryEmpty(File pDirectory) {
        String[] files = pDirectory.list();
        return files == null || files.length == 0;
    }

    // TODO: Customize the warning alert and move it to a separate class
    private void showWarningAlert(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.WARNING);

            Image icon = ImageLoader.getImage("/assets/images/logo.png");
            ImageView imageView = new ImageView(icon);
            imageView.setFitWidth(50);
            imageView.setPreserveRatio(true);

            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.setGraphic(imageView);

            alert.showAndWait();
        });
    }


}
