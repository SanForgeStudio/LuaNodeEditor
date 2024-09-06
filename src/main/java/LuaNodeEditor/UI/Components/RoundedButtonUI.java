package LuaNodeEditor.UI.Components;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class RoundedButtonUI extends BasicButtonUI {

    private final Color normalColor;
    private final Color hoverColor;
    private final int arc;

    public RoundedButtonUI(Color pNormalColor, Color pHoverColor, int pArc) {
        this.normalColor = pNormalColor;
        this.hoverColor = pHoverColor;
        this.arc = pArc;
    }

    @Override
    public void paint(Graphics pGraphics, JComponent pComponent) {
        AbstractButton button = (AbstractButton) pComponent;
        Graphics2D g2d = (Graphics2D) pGraphics;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = button.getWidth();
        int height = button.getHeight();
        Color backgroundColor = button.getModel().isRollover() ? hoverColor : normalColor;
        g2d.setColor(backgroundColor);
        g2d.fillRoundRect(0, 0, width, height, arc, arc);

        g2d.setColor(button.getForeground());
        FontMetrics fm = g2d.getFontMetrics();
        String text = button.getText();
        int x = (width - fm.stringWidth(text)) / 2;
        int y = ((height - fm.getHeight()) / 2) + fm.getAscent();
        g2d.drawString(text, x, y);
    }
}
