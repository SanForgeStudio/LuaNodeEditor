package LuaNodeEditor.UI.Components;

import javafx.geometry.Insets;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class BasePopupMenu extends ContextMenu {

    private final Map<String, CustomMenuItem> menuItems = new HashMap<>();

    public BasePopupMenu(Color pBackgroundColor, Color pItemColor, Color pItemHoverColor, MenuItem... pItems) {
        initializeMenu(pBackgroundColor, pItemColor, pItemHoverColor, pItems);
    }

    private void initializeMenu(Color pBackgroundColor, Color pItemColor, Color pItemHoverColor, MenuItem... pItems) {
        this.setStyle("-fx-background-color: " + toHexString(pBackgroundColor) + ";");

        for (MenuItem item : pItems) {
            Label label = new Label(item.getText());
            label.setPadding(new Insets(5));
            label.setBackground(new Background(new BackgroundFill(pItemColor, new CornerRadii(5), Insets.EMPTY)));

            CustomMenuItem customMenuItem = new CustomMenuItem(label, false);

            this.getItems().add(customMenuItem);
            menuItems.put(item.getText(), customMenuItem);
        }
    }

    public CustomMenuItem getMenuItem(String pText) {
        return menuItems.get(pText);
    }

    private String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
