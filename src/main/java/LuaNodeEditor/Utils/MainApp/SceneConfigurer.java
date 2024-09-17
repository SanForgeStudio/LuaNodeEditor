package LuaNodeEditor.Utils.MainApp;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SceneConfigurer {

    public static void configureScene(Stage pStage, BorderPane pRoot) {
        Scene scene = new Scene(pRoot);
        pStage.setScene(scene);
    }
}
