package LuaNodeEditor.UI.TitleBar.Buttons;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {

    public ButtonPanel(JFrame frame) {
        setLayout(new GridBagLayout());
        setOpaque(false);

        // Add buttons to panel
        add(new MinimizeButton(frame), createGridBagConstraints());
        add(new MaximizeButton(frame), createGridBagConstraints());
        add(new CloseButton(frame), createGridBagConstraints());
    }

    private GridBagConstraints createGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = GridBagConstraints.RELATIVE;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.CENTER;
        return gbc;
    }
}
