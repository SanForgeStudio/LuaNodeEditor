package LuaNodeEditor.UI.Components;

import javax.swing.*;
import java.awt.*;

public class BasePanel extends JPanel {

    public BasePanel(Color pBackgroundColor, int pHeight) {
        setBackground(pBackgroundColor);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(getWidth(), pHeight));
    }
}
