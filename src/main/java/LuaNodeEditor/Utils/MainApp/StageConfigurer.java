package LuaNodeEditor.Utils.MainApp;

import LuaNodeEditor.Logging.BaseLogger;
import LuaNodeEditor.UI.Components.ImageLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageConfigurer {

    private static final String TITLE = "Lua Node Editor";
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 800;
    private static final String LOGO_PATH = "/assets/images/logo.png";

    public static void configureStage(Stage pStage) {
        pStage.setTitle(TITLE);
        pStage.setWidth(DEFAULT_WIDTH);
        pStage.setHeight(DEFAULT_HEIGHT);
        pStage.centerOnScreen();
        pStage.initStyle(StageStyle.UNDECORATED);
        pStage.getIcons().add(loadIcon());

        BaseLogger.logSuccess("Stage configured successfully.");
    }

    private static Image loadIcon() {
        try {
            BaseLogger.logSuccess("Application icon loaded successfully.");
            return ImageLoader.getImage(LOGO_PATH);
        } catch (Exception pException) {
            BaseLogger.logError("Failed to load application icon.", pException);
            return null;
        }
    }
}
