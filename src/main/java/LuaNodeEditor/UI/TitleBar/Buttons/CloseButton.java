package LuaNodeEditor.UI.TitleBar.Buttons;

import LuaNodeEditor.UI.Components.TextButton;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CloseButton extends TextButton {

    public CloseButton(Stage pStage) {
        super("x", Color.rgb(60, 60, 60), Color.rgb(255, 10, 10), 40, 30);

        setOnMouseEntered(event -> setBackground(new Background(new BackgroundFill(Color.rgb(255, 10, 10), new CornerRadii(5), Insets.EMPTY))));
        setOnMouseExited(event -> setBackground(new Background(new BackgroundFill(Color.rgb(60, 60, 60), new CornerRadii(5), Insets.EMPTY))));

        setOnAction(event -> pStage.close());

        setPrefSize(40, 30);
    }
}
