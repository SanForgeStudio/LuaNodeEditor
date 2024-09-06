package LuaNodeEditor.UI.Components;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BasePopupMenu extends JPopupMenu {

    private final Color backgroundColor;
    private final Color itemColor;
    private final Color itemHoverColor;
    private final Map<String, JMenuItem> menuItems = new HashMap<>();

    public BasePopupMenu(Color pBackgroundColor, Color pItemColor, Color pItemHoverColor, String... pItems) {
        this.backgroundColor = pBackgroundColor;
        this.itemColor = pItemColor;
        this.itemHoverColor = pItemHoverColor;
        initializeMenu(pItems);
    }

    private void initializeMenu(String... pItems) {
        setBackground(backgroundColor);

        for (String itemText : pItems) {
            JMenuItem menuItem = new JMenuItem(itemText);
            menuItem.setBackground(itemColor);
            menuItem.setForeground(Color.WHITE);
            menuItem.setOpaque(true);
            menuItem.setBorder(BorderFactory.createEmptyBorder());

            menuItem.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent pEvent) {
                    menuItem.setBackground(itemHoverColor);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent pEvent) {
                    menuItem.setBackground(itemColor);
                }
            });

            add(menuItem);
            menuItems.put(itemText, menuItem);
        }
    }

    public JMenuItem getMenuItem(String pText) {
        return menuItems.get(pText);
    }
}
