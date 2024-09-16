package LuaNodeEditor;

import LuaNodeEditor.Listener.WindowDragger;
import LuaNodeEditor.Listener.WindowResizer;
import LuaNodeEditor.Logging.BaseLogger;
import LuaNodeEditor.UI.Components.ImageLoader;
import LuaNodeEditor.UI.ContentBrowser.ContentBrowser;
import LuaNodeEditor.UI.MainPanel;
import LuaNodeEditor.UI.TitleBar.TitleBar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LuaNodeEditor extends Application {

    public static ContentBrowser contentBrowser;
    public static BorderPane root;
    public static MainPanel mainPanel;

    @Override
    public void start(Stage pPrimaryStage) {
        try {
            pPrimaryStage.setTitle("Lua Node Editor");
            pPrimaryStage.setWidth(1200);
            pPrimaryStage.setHeight(800);
            pPrimaryStage.centerOnScreen();

            pPrimaryStage.initStyle(StageStyle.UNDECORATED);

            Image icon = ImageLoader.getImage("/assets/images/logo.png");
            pPrimaryStage.getIcons().add(icon);

            root = new BorderPane();
            root.setStyle("-fx-background-color: #232323;");

            TitleBar titleBar = new TitleBar(pPrimaryStage);
            WindowDragger windowDragger = new WindowDragger();
            windowDragger.enableWindowDrag(pPrimaryStage, titleBar);
            WindowResizer windowResizer = new WindowResizer();
            windowResizer.enableWindowResize(pPrimaryStage, root);
            mainPanel = new MainPanel();

            contentBrowser = new ContentBrowser();
            root.setTop(titleBar);
            root.setCenter(mainPanel);

            Scene scene = new Scene(root);
            pPrimaryStage.setScene(scene);

            pPrimaryStage.show();
        } catch (Exception pException) {
            BaseLogger.logError("An error occurred while starting the application.", pException);
        }
    }

    public static void main(String[] pArgs) {
        launch(pArgs);
    }
}