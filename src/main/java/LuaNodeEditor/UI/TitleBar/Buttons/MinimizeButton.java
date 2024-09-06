package LuaNodeEditor.UI.TitleBar.Buttons;

import LuaNodeEditor.UI.Components.TextButton;

import javax.swing.*;
import java.awt.*;

public class MinimizeButton extends TextButton {

    public MinimizeButton(JFrame pFrame) {
        super("-", new Color(60, 60, 60), new Color(80, 80, 80));
        addActionListener(e -> pFrame.setState(JFrame.ICONIFIED));
    }
}
