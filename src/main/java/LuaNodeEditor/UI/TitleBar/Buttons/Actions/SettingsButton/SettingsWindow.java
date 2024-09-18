package LuaNodeEditor.UI.TitleBar.Buttons.Actions.SettingsButton;

import LuaNodeEditor.Logging.BaseLogger;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingsWindow {

    public SettingsWindow(Stage pParentStage) {
        Stage settingsStage = new Stage();
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsStage.initOwner(pParentStage);
        settingsStage.setTitle("Settings");

        CheckBox loggingCheckBox = new CheckBox("Logging");
        loggingCheckBox.setSelected(true);
        loggingCheckBox.selectedProperty().addListener((pObservable, pOldValue, pNewValue) -> {
            BaseLogger.setLoggingEnabled(pNewValue);
        });

        VBox layout = new VBox(10);
        layout.getChildren().add(loggingCheckBox);

        Scene scene = new Scene(layout, 200, 100);
        settingsStage.setScene(scene);
        settingsStage.showAndWait();

        BaseLogger.logSuccess("Settings window opened");
    }
}
