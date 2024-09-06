package LuaNodeEditor.UI.Components;

import javax.swing.*;
import java.awt.*;

public class BasePanel extends JPanel {

    public BasePanel(Color backgroundColor, int height) {
        setBackground(backgroundColor);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(getWidth(), height));
    }
}
