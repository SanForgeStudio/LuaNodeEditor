package LuaNodeEditor.UI.TitleBar;

import javax.swing.*;
import java.awt.*;

import LuaNodeEditor.UI.Components.*;
import LuaNodeEditor.UI.Helpers.GridBagHelper; // Import the GridBagHelper
import LuaNodeEditor.UI.Listeners.MouseDragListener;
import LuaNodeEditor.UI.TitleBar.Buttons.*;

public class TitleBar extends BasePanel {

    public TitleBar(JFrame pFrame) {
        super(new Color(45, 45, 45), 30);

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setOpaque(false);

        GridBagConstraints gbc = GridBagHelper.createStandardConstraints();

        ImageLoader logoLoader = new ImageLoader("/assets/images/logo.png", 25, 25);
        gbc.gridx = 0;
        leftPanel.add(logoLoader, gbc);

        FileButton fileButton = new FileButton();
        gbc.gridx = 1;
        leftPanel.add(fileButton, gbc);

        add(leftPanel, BorderLayout.WEST);

        ButtonPanel buttonPanel = new ButtonPanel(pFrame);
        add(buttonPanel, BorderLayout.EAST);

        MouseDragListener dragListener = new MouseDragListener(pFrame);
        addMouseListener(dragListener);
        addMouseMotionListener(dragListener);
    }
}
