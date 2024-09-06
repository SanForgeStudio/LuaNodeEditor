package LuaNodeEditor.UI.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TextButton extends JButton {

    public TextButton(String pText, Color pNormalColor, Color pHoverColor) {
        super(pText);
        setForeground(Color.WHITE);
        setBackground(pNormalColor);
        setBorder(BorderFactory.createEmptyBorder());
        setPreferredSize(new Dimension(40, 30));
        setFocusPainted(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent pEvent) {
                setBackground(pHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent pEvent) {
                setBackground(pNormalColor);
            }

            @Override
            public void mousePressed(MouseEvent pEvent) {
                setBackground(pHoverColor);
            }

            @Override
            public void mouseDragged(MouseEvent pEvent) {
                setBackground(pHoverColor);
            }

            @Override
            public void mouseReleased(MouseEvent pEvent) {
                if (getBounds().contains(pEvent.getPoint())) {
                    setBackground(pHoverColor);
                } else {
                    setBackground(pNormalColor);
                }
            }
        });
    }
}
