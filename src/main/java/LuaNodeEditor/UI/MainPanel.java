package LuaNodeEditor.UI;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class MainPanel extends BorderPane {

    private static final int GRID_SIZE = 25;
    private static final Color GRID_COLOR = Color.rgb(75, 75, 75);

    private final Canvas canvas;
    private final GraphicsContext graphicsContext;

    public MainPanel() {
        canvas = new Canvas();
        graphicsContext = canvas.getGraphicsContext2D();

        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());

        widthProperty().addListener((observable, oldValue, newValue) -> drawGrid());
        heightProperty().addListener((observable, oldValue, newValue) -> drawGrid());

        setCenter(canvas);
    }

    private void drawGrid() {
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        graphicsContext.setFill(Color.rgb(35, 35, 35));
        graphicsContext.fillRect(0, 0, width, height);

        graphicsContext.setStroke(GRID_COLOR);

        for (int x = 0; x < width; x += GRID_SIZE) {
            for (int y = 0; y < height; y += GRID_SIZE) {
                graphicsContext.strokeRect(x, y, GRID_SIZE, GRID_SIZE);
            }
        }
    }
}
