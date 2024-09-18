package LuaNodeEditor.UI.MainPanel;

import LuaNodeEditor.Logging.BaseLogger;
import LuaNodeEditor.LuaNodeEditor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GridDrawer {

    private static final int GRID_SIZE = 25;
    private static final Color GRID_COLOR = Color.rgb(75, 75, 75);

    public void drawGrid(GraphicsContext pGraphics, double pWidth, double pHeight) {
        pGraphics.setFill(LuaNodeEditor.BACKGROUND_COLOR);
        pGraphics.fillRect(0, 0, pWidth, pHeight);

        pGraphics.setStroke(GRID_COLOR);
        for (int x = 0; x < pWidth; x += GRID_SIZE) {
            for (int y = 0; y < pHeight; y += GRID_SIZE) {
                pGraphics.strokeRect(x, y, GRID_SIZE, GRID_SIZE);
            }
        }
    }

    public int snapToGrid(double pCoord) {
        return (int) (Math.floor(pCoord / GRID_SIZE) * GRID_SIZE);
    }
}
