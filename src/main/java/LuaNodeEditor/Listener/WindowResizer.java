package LuaNodeEditor.Listener;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WindowResizer {

    private static final int RESIZE_MARGIN = 5;
    private boolean isResizing = false;
    private double startX;
    private double startY;

    public void enableWindowResize(Stage stage, Pane pane) {
        pane.setOnMouseMoved(event -> setCursor(event, pane, stage));
        pane.setOnMousePressed(event -> mousePressed(event, stage));
        pane.setOnMouseDragged(event -> mouseDragged(event, stage, pane));
        pane.setOnMouseReleased(event -> isResizing = false);
    }

    private void setCursor(MouseEvent event, Pane pane, Stage stage) {
        if (isInResizeMargin(event, pane)) {
            if (isInTopLeftCorner(event, pane)) {
                pane.setCursor(javafx.scene.Cursor.NW_RESIZE);
            } else if (isInTopRightCorner(event, pane)) {
                pane.setCursor(javafx.scene.Cursor.NE_RESIZE);
            } else if (isInBottomLeftCorner(event, pane)) {
                pane.setCursor(javafx.scene.Cursor.SW_RESIZE);
            } else if (isInBottomRightCorner(event, pane)) {
                pane.setCursor(javafx.scene.Cursor.SE_RESIZE);
            } else if (isInLeftResizeZone(event)) {
                pane.setCursor(javafx.scene.Cursor.W_RESIZE);
            } else if (isInRightResizeZone(event, pane)) {
                pane.setCursor(javafx.scene.Cursor.E_RESIZE);
            } else if (isInTopResizeZone(event)) {
                pane.setCursor(javafx.scene.Cursor.N_RESIZE);
            } else if (isInBottomResizeZone(event, pane)) {
                pane.setCursor(javafx.scene.Cursor.S_RESIZE);
            }
        } else {
            pane.setCursor(javafx.scene.Cursor.DEFAULT);
        }
    }

    private void mousePressed(MouseEvent event, Stage stage) {
        isResizing = true;
        startX = event.getScreenX();
        startY = event.getScreenY();
    }

    private void mouseDragged(MouseEvent event, Stage stage, Pane pane) {
        if (isResizing) {
            double deltaX = event.getScreenX() - startX;
            double deltaY = event.getScreenY() - startY;

            if (pane.getCursor() == javafx.scene.Cursor.NW_RESIZE || pane.getCursor() == javafx.scene.Cursor.W_RESIZE) {
                stage.setX(stage.getX() + deltaX);
                stage.setWidth(stage.getWidth() - deltaX);
            }
            if (pane.getCursor() == javafx.scene.Cursor.NW_RESIZE || pane.getCursor() == javafx.scene.Cursor.N_RESIZE) {
                stage.setY(stage.getY() + deltaY);
                stage.setHeight(stage.getHeight() - deltaY);
            }
            if (pane.getCursor() == javafx.scene.Cursor.NE_RESIZE || pane.getCursor() == javafx.scene.Cursor.E_RESIZE) {
                stage.setWidth(stage.getWidth() + deltaX);
            }
            if (pane.getCursor() == javafx.scene.Cursor.SE_RESIZE || pane.getCursor() == javafx.scene.Cursor.S_RESIZE) {
                stage.setHeight(stage.getHeight() + deltaY);
            }
            if (pane.getCursor() == javafx.scene.Cursor.SE_RESIZE || pane.getCursor() == javafx.scene.Cursor.S_RESIZE) {
                stage.setWidth(stage.getWidth() + deltaX);
                stage.setHeight(stage.getHeight() + deltaY);
            }
            startX = event.getScreenX();
            startY = event.getScreenY();
        }
    }

    private boolean isInResizeMargin(MouseEvent event, Pane pane) {
        return event.getX() < RESIZE_MARGIN || event.getX() > pane.getWidth() - RESIZE_MARGIN
                || event.getY() < RESIZE_MARGIN || event.getY() > pane.getHeight() - RESIZE_MARGIN;
    }

    private boolean isInLeftResizeZone(MouseEvent event) {
        return event.getX() < RESIZE_MARGIN;
    }

    private boolean isInRightResizeZone(MouseEvent event, Pane pane) {
        return event.getX() > pane.getWidth() - RESIZE_MARGIN;
    }

    private boolean isInTopResizeZone(MouseEvent event) {
        return event.getY() < RESIZE_MARGIN;
    }

    private boolean isInBottomResizeZone(MouseEvent event, Pane pane) {
        return event.getY() > pane.getHeight() - RESIZE_MARGIN;
    }

    private boolean isInTopLeftCorner(MouseEvent event, Pane pane) {
        return isInLeftResizeZone(event) && isInTopResizeZone(event);
    }

    private boolean isInTopRightCorner(MouseEvent event, Pane pane) {
        return isInRightResizeZone(event, pane) && isInTopResizeZone(event);
    }

    private boolean isInBottomLeftCorner(MouseEvent event, Pane pane) {
        return isInLeftResizeZone(event) && isInBottomResizeZone(event, pane);
    }

    private boolean isInBottomRightCorner(MouseEvent event, Pane pane) {
        return isInRightResizeZone(event, pane) && isInBottomResizeZone(event, pane);
    }
}
