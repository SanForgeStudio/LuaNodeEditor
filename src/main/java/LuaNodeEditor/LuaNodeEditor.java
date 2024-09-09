package LuaNodeEditor;

import LuaNodeEditor.UI.Components.ImageLoader;
import LuaNodeEditor.UI.MainPanel;
import LuaNodeEditor.UI.TitleBar.TitleBar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LuaNodeEditor extends Application {

    @Override
    public void start(Stage pPrimaryStage) {
        pPrimaryStage.setTitle("Lua Node Editor");
        pPrimaryStage.setWidth(1200);
        pPrimaryStage.setHeight(800);
        pPrimaryStage.centerOnScreen();

        pPrimaryStage.initStyle(StageStyle.UNDECORATED);

        Image icon = ImageLoader.getImage("/assets/images/logo.png");
        pPrimaryStage.getIcons().add(icon);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #232323;");

        TitleBar titleBar = new TitleBar(pPrimaryStage);
        MainPanel mainPanel = new MainPanel();

        root.setTop(titleBar);
        root.setCenter(mainPanel);

        Scene scene = new Scene(root);
        pPrimaryStage.setScene(scene);

        pPrimaryStage.show();
    }

    public static void main(String[] pArgs) {
        launch(pArgs);
    }
}
