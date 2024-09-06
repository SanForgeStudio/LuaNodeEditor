package LuaNodeEditor.UI.TitleBar.Buttons;

import LuaNodeEditor.UI.Components.TextButton;

import javax.swing.*;
import java.awt.*;

public class CloseButton extends TextButton {

    public CloseButton(JFrame pFrame) {
        super("x", new Color(60, 60, 60), new Color(255, 10, 10));
        addActionListener(e -> pFrame.dispose());
    }
}
