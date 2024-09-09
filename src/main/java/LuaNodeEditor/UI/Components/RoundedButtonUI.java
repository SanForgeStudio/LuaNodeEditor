package LuaNodeEditor.UI.Components;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.geometry.Insets;

public class RoundedButtonUI extends Button {

    public RoundedButtonUI(String pText, Color pNormalColor, Color pHoverColor, double pArc) {
        super(pText);

        setFont(new Font(14));
        setTextFill(Color.WHITE);
        setBackground(new Background(new BackgroundFill(pNormalColor, new CornerRadii(pArc), Insets.EMPTY)));

        setOnMouseEntered(event -> setBackground(new Background(new BackgroundFill(pHoverColor, new CornerRadii(pArc), Insets.EMPTY))));
        setOnMouseExited(event -> setBackground(new Background(new BackgroundFill(pNormalColor, new CornerRadii(pArc), Insets.EMPTY))));
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();

        Text text = (Text) lookup(".text");
        if (text != null) {
            text.setTranslateX((getWidth() - text.getBoundsInLocal().getWidth()) / 2);
            text.setTranslateY((getHeight() - text.getBoundsInLocal().getHeight()) / 2);
        }
    }
}
