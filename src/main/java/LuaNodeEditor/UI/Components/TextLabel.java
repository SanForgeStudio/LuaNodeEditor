package LuaNodeEditor.UI.Components;

import javax.swing.*;
import java.awt.*;

public class TextLabel extends JLabel {

    public TextLabel(String pText) {
        super(pText);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.PLAIN, 15));
    }
}
