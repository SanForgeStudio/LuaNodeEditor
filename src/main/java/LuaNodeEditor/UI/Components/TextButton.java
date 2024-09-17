package LuaNodeEditor.UI.Components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextButton extends Button {

    public static final Color NORMAL_COLOR = Color.rgb(60, 60, 60);
    public static final Color HOVER_COLOR = Color.rgb(80, 80, 80);

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
