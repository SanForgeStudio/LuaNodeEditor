package LuaNodeEditor.UI;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private static final int GRID_SIZE = 25;
    private static final Color GRID_COLOR = Color.DARK_GRAY;

    public MainPanel() {
        setLayout(new BorderLayout());
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics pGraphics) {
        super.paintComponent(pGraphics);

        pGraphics.setColor(new Color(35, 35, 35));
        pGraphics.fillRect(0, 0, getWidth(), getHeight());

        pGraphics.setColor(GRID_COLOR);

        for (int x = 0; x < getWidth(); x += GRID_SIZE) {
            for (int y = 0; y < getHeight(); y += GRID_SIZE) {
                pGraphics.drawRect(x, y, GRID_SIZE, GRID_SIZE);
            }
        }
    }
}
