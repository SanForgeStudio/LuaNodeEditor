package LuaNodeEditor.UI.TitleBar.Buttons;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MaximizeButton extends Button {

    public MaximizeButton(Stage pStage) {
        setText("+");
        setFont(new Font(16));
        setTextFill(Color.WHITE);
        setBackground(new Background(new BackgroundFill(Color.rgb(60, 60, 60), new CornerRadii(5), Insets.EMPTY)));

        setOnMouseEntered(event -> setBackground(new Background(new BackgroundFill(Color.rgb(80, 80, 80), new CornerRadii(5), Insets.EMPTY))));
        setOnMouseExited(event -> setBackground(new Background(new BackgroundFill(Color.rgb(60, 60, 60), new CornerRadii(5), Insets.EMPTY))));

        setOnAction(event -> {
            pStage.setMaximized(!pStage.isMaximized());
        });

        setPrefSize(40, 30);
    }
}
