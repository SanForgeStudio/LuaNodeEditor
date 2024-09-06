package LuaNodeEditor.UI.TitleBar.Buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaximizeButton extends JButton {

    public MaximizeButton(JFrame frame) {
        super("+");
        setForeground(Color.WHITE);
        setBackground(new Color(60, 60, 60));
        setBorder(BorderFactory.createEmptyBorder());
        setPreferredSize(new Dimension(40, 30));
        setFocusPainted(false);
        setFont(new Font("Arial", Font.BOLD, 12));
        setBackground(new Color(80, 80, 80));

        addActionListener(e -> {
            if (frame.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                frame.setExtendedState(JFrame.NORMAL);
            } else {
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                setBackground(new Color(100, 100, 100));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                setBackground(new Color(80, 80, 80));
            }
        });
    }
}
