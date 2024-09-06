package LuaNodeEditor.UI.TitleBar.Buttons;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {

    public ButtonPanel(JFrame pFrame) {
        setLayout(new GridBagLayout());
        setOpaque(false);

        add(new MinimizeButton(pFrame), createGridBagConstraints());
        add(new MaximizeButton(pFrame), createGridBagConstraints());
        add(new CloseButton(pFrame), createGridBagConstraints());
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
