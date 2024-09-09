package LuaNodeEditor.UI.Components;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;

public class TextButton extends Button {

    public TextButton(String pText, Color pNormalColor, Color pHoverColor, double pWidth, double pHeight) {
        super(pText);
        setTextFill(Color.WHITE);
        setBackground(new Background(new BackgroundFill(pNormalColor, new CornerRadii(5), Insets.EMPTY)));
        setPrefSize(pWidth, pHeight);
        setFont(Font.font(14));

        // Mouse hover effect
        setOnMouseEntered(event -> setBackground(new Background(new BackgroundFill(pHoverColor, new CornerRadii(5), Insets.EMPTY))));
        setOnMouseExited(event -> setBackground(new Background(new BackgroundFill(pNormalColor, new CornerRadii(5), Insets.EMPTY))));

        // Mouse pressed and released effect
        setOnMousePressed(event -> setBackground(new Background(new BackgroundFill(pHoverColor, new CornerRadii(5), Insets.EMPTY))));
        setOnMouseReleased(event -> setBackground(new Background(new BackgroundFill(pNormalColor, new CornerRadii(5), Insets.EMPTY))));
    }
}
