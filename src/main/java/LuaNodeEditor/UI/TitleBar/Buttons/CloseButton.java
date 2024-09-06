package LuaNodeEditor.UI.TitleBar.Buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseButton extends JButton {

    public CloseButton(JFrame frame) {
        super("x");
        setForeground(Color.WHITE);
        setBackground(new Color(60, 60, 60));
        setBorder(BorderFactory.createEmptyBorder());
        setPreferredSize(new Dimension(40, 30));
        setFocusPainted(false);
        setFont(new Font("Arial", Font.BOLD, 12));
        setBackground(new Color(255, 10, 10));

        addActionListener(e -> frame.dispose());

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                setBackground(new Color(200, 10, 10));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                setBackground(new Color(255, 10, 10));
            }
        });
    }
}
