package LuaNodeEditor.Listener;

import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class WindowDragger {

    private double xOffset = 0;
    private double yOffset = 0;

        public void enableWindowDrag(Stage stage, Region titleBar) {
        titleBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }
}
