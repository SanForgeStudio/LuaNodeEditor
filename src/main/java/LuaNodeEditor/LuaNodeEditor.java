package LuaNodeEditor;

import LuaNodeEditor.Logging.BaseLogger;
import LuaNodeEditor.UI.ContentBrowser.ContentBrowser;
import LuaNodeEditor.UI.MainPanel.MainPanel;
import LuaNodeEditor.UI.TitleBar.TitleBar;
import LuaNodeEditor.Utils.MainApp.SceneConfigurer;
import LuaNodeEditor.Utils.MainApp.StageConfigurer;
import LuaNodeEditor.Utils.MainApp.WindowInteractionHandler;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LuaNodeEditor extends Application {

    public static ContentBrowser contentBrowser;
    public static BorderPane root;
    public static MainPanel mainPanel;
    public static final Color BACKGROUND_COLOR = Color.rgb(35, 35, 35);

    @Override
    public void start(Stage pPrimaryStage) {
        try {
            StageConfigurer.configureStage(pPrimaryStage);

            initializeComponents(pPrimaryStage);

            SceneConfigurer.configureScene(pPrimaryStage, root);

            pPrimaryStage.show();
            BaseLogger.logSuccess("LuaNodeEditor application started successfully.");
        } catch (Exception pException) {
            BaseLogger.logError("An error occurred while starting the LuaNodeEditor application.", pException);
        }
    }

    private void initializeComponents(Stage pPrimaryStage) {
        root = new BorderPane();
        root.setStyle("-fx-background-color: #232323;");

        TitleBar titleBar = new TitleBar(pPrimaryStage);
        mainPanel = new MainPanel();
        contentBrowser = new ContentBrowser();

        WindowInteractionHandler.setupWindowInteractions(pPrimaryStage, titleBar, root);

        root.setTop(titleBar);
        root.setCenter(mainPanel);

        BaseLogger.logSuccess("Components initialized successfully.");
    }

    public static void main(String[] pArgs) {
        launch(pArgs);
    }
}
