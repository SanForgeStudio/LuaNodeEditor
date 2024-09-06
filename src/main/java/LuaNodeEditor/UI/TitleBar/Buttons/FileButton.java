package LuaNodeEditor.UI.TitleBar.Buttons;

import LuaNodeEditor.UI.Components.BasePopupMenu;
import LuaNodeEditor.UI.Components.RoundedButtonUI;
import LuaNodeEditor.UI.Components.TextButton;
import LuaNodeEditor.UI.TitleBar.Buttons.Actions.*;

import java.awt.*;

public class FileButton extends TextButton {

    public FileButton() {
        super("File", new Color(45, 45, 45), new Color(45, 45, 45), new Dimension(40, 20));
        Color normalColor = new Color(45, 45, 45);
        Color hoverColor = new Color(80, 80, 80);
        int arc = 10;

        setUI(new RoundedButtonUI(normalColor, hoverColor, arc));

        BasePopupMenu popupMenu = new BasePopupMenu(
                new Color(45, 45, 45),
                new Color(55, 55, 55),
                new Color(80, 80, 80),
                "New", "Load", "Save", "Save As"
        );

        popupMenu.getMenuItem("New").addActionListener(new NewAction());
        popupMenu.getMenuItem("Load").addActionListener(new LoadAction());
        popupMenu.getMenuItem("Save").addActionListener(new SaveAction());
        popupMenu.getMenuItem("Save As").addActionListener(new SaveAsAction());

        addActionListener(e -> popupMenu.show(FileButton.this, 0, FileButton.this.getHeight()));
    }
}
