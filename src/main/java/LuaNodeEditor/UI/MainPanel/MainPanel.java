package LuaNodeEditor.UI.MainPanel;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class MainPanel extends BorderPane {

    public static Canvas canvas = new Canvas();
    private final GraphicsContext graphicsContext;
    private final GridDrawer gridDrawer;

    public MainPanel() {
        graphicsContext = canvas.getGraphicsContext2D();
        gridDrawer = new GridDrawer();

        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());

        widthProperty().addListener((observable, oldValue, newValue) -> drawGrid());
        heightProperty().addListener((observable, oldValue, newValue) -> drawGrid());

        setCenter(canvas);

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleMouseClick);
    }

    private void drawGrid() {
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        gridDrawer.drawGrid(graphicsContext, width, height);
    }

    // TODO: Code under here is purely to test the GridSnapping feature
    private void handleMouseClick(MouseEvent pEvent) {
        double mouseX = pEvent.getX();
        double mouseY = pEvent.getY();

        int snappedX = gridDrawer.snapToGrid(mouseX);
        int snappedY = gridDrawer.snapToGrid(mouseY);

        drawBlock(snappedX, snappedY);
    }

    private void drawBlock(int pX, int pY) {
        int blockSize = 25;

        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillRect(pX, pY, blockSize, blockSize);

        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.strokeRect(pX, pY, blockSize, blockSize);
    }
}
