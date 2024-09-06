package LuaNodeEditor.UI.TitleBar;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import LuaNodeEditor.LuaNodeEditor;
import LuaNodeEditor.UI.TitleBar.Buttons.*;

public class TitleBar extends JPanel {

    public TitleBar(JFrame frame) {
        setBackground(new Color(45, 45, 45));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(frame.getWidth(), 30));

        // Add logo
        URL iconURL = LuaNodeEditor.class.getResource("/assets/images/logo.png");
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(new ImageIcon(iconURL).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            JLabel logoLabel = new JLabel(icon);
            logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            add(logoLabel, BorderLayout.WEST);
        }

        // Add title
        JLabel titleLabel = new JLabel("Lua Node Editor");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        add(titleLabel, BorderLayout.CENTER);

        // Add buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.add(new MinimizeButton(frame), createGridBagConstraints());
        buttonPanel.add(new MaximizeButton(frame), createGridBagConstraints());
        buttonPanel.add(new CloseButton(frame), createGridBagConstraints());
        add(buttonPanel, BorderLayout.EAST);
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
