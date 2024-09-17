package LuaNodeEditor.UI.MainPanel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GridDrawer {

    private static final int GRID_SIZE = 25;
    private static final Color GRID_COLOR = Color.rgb(75, 75, 75);

    public void drawGrid(GraphicsContext graphicsContext, double width, double height) {
        graphicsContext.setFill(Color.rgb(35, 35, 35));
        graphicsContext.fillRect(0, 0, width, height);

        graphicsContext.setStroke(GRID_COLOR);
        for (int x = 0; x < width; x += GRID_SIZE) {
            for (int y = 0; y < height; y += GRID_SIZE) {
                graphicsContext.strokeRect(x, y, GRID_SIZE, GRID_SIZE);
            }
        }
    }
    
    public int snapToGrid(double coordinate) {
        return (int) (Math.floor(coordinate / GRID_SIZE) * GRID_SIZE);
    }
}
