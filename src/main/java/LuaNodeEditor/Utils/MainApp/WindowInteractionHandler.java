package LuaNodeEditor.Utils.MainApp;

import LuaNodeEditor.Listener.WindowDragger;
import LuaNodeEditor.Listener.WindowResizer;
import LuaNodeEditor.Logging.BaseLogger;
import LuaNodeEditor.UI.TitleBar.TitleBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WindowInteractionHandler {

    public static void setupWindowInteractions(Stage pStage, TitleBar pTitleBar, BorderPane pRoot) {
        WindowDragger windowDragger = new WindowDragger();
        windowDragger.enableWindowDrag(pStage, pTitleBar);

        WindowResizer windowResizer = new WindowResizer();
        windowResizer.enableWindowResize(pStage, pRoot);

        BaseLogger.logSuccess("Window interactions setup successfully.");
    }
}
