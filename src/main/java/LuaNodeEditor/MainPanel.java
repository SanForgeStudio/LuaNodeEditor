package LuaNodeEditor;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel() {
        setLayout(new BorderLayout());
        setOpaque(false);

        JLabel contentLabel = new JLabel("Content Area", SwingConstants.CENTER);
        contentLabel.setForeground(Color.WHITE);

        add(contentLabel, BorderLayout.CENTER);
    }
}
