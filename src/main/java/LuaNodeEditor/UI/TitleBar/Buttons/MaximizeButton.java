package LuaNodeEditor.UI.TitleBar.Buttons;

import LuaNodeEditor.UI.Components.TextButton;

import javax.swing.*;
import java.awt.*;

public class MaximizeButton extends TextButton {

    public MaximizeButton(JFrame pFrame) {
        super("+", new Color(60, 60, 60), new Color(80, 80, 80), new Dimension(40, 30));
        addActionListener(e -> {
            if (pFrame.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                pFrame.setExtendedState(JFrame.NORMAL);
            } else {
                pFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
    }
}
