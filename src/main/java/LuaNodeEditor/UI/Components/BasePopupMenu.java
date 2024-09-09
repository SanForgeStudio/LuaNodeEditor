package LuaNodeEditor.UI.Components;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.layout.Region;

import java.util.HashMap;
import java.util.Map;

public class BasePopupMenu extends ContextMenu {

    private final Map<String, MenuItem> menuItems = new HashMap<>();

    public BasePopupMenu(Color pBackgroundColor, Color pItemColor, Color pItemHoverColor, String... pItems) {
        initializeMenu(pBackgroundColor, pItemColor, pItemHoverColor, pItems);
    }

    private void initializeMenu(Color pBackgroundColor, Color pItemColor, Color pItemHoverColor, String... pItems) {
        for (String itemText : pItems) {
            MenuItem menuItem = new MenuItem(itemText);
            Region backgroundRegion = new Region();
            backgroundRegion.setBackground(new Background(new BackgroundFill(pItemColor, new CornerRadii(5), Insets.EMPTY)));
            CustomMenuItem customMenuItem = new CustomMenuItem();

            this.getItems().add(customMenuItem);
            menuItems.put(itemText, menuItem);
        }
    }

    public MenuItem getMenuItem(String pText) {
        return menuItems.get(pText);
    }
}
