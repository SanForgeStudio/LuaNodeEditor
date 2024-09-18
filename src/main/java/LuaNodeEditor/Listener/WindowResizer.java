package LuaNodeEditor.Listener;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WindowResizer {

    private static final int RESIZE_MARGIN = 5;
    private boolean isResizing = false;
    private double startX;
    private double startY;

    public void enableWindowResize(Stage pStage, Pane pPane) {
        pPane.setOnMouseMoved(event -> setCursor(event, pPane));
        pPane.setOnMousePressed(this::mousePressed);
        pPane.setOnMouseDragged(event -> mouseDragged(event, pStage, pPane));
        pPane.setOnMouseReleased(event -> isResizing = false);
    }

    private void setCursor(MouseEvent pEvent, Pane pPane) {
        if (isInResizeMargin(pEvent, pPane)) {
            if (isInTopLeftCorner(pEvent)) {
                pPane.setCursor(javafx.scene.Cursor.NW_RESIZE);
            } else if (isInTopRightCorner(pEvent, pPane)) {
                pPane.setCursor(javafx.scene.Cursor.NE_RESIZE);
            } else if (isInBottomLeftCorner(pEvent, pPane)) {
                pPane.setCursor(javafx.scene.Cursor.SW_RESIZE);
            } else if (isInBottomRightCorner(pEvent, pPane)) {
                pPane.setCursor(javafx.scene.Cursor.SE_RESIZE);
            } else if (isInLeftResizeZone(pEvent)) {
                pPane.setCursor(javafx.scene.Cursor.W_RESIZE);
            } else if (isInRightResizeZone(pEvent, pPane)) {
                pPane.setCursor(javafx.scene.Cursor.E_RESIZE);
            } else if (isInTopResizeZone(pEvent)) {
                pPane.setCursor(javafx.scene.Cursor.N_RESIZE);
            } else if (isInBottomResizeZone(pEvent, pPane)) {
                pPane.setCursor(javafx.scene.Cursor.S_RESIZE);
            }
        } else {
            pPane.setCursor(javafx.scene.Cursor.DEFAULT);
        }
    }

    private void mousePressed(MouseEvent pEvent) {
        isResizing = true;
        startX = pEvent.getScreenX();
        startY = pEvent.getScreenY();
    }

    private void mouseDragged(MouseEvent pEvent, Stage pStage, Pane pPane) {
        if (isResizing) {
            double deltaX = pEvent.getScreenX() - startX;
            double deltaY = pEvent.getScreenY() - startY;

            if (pPane.getCursor() == javafx.scene.Cursor.NW_RESIZE || pPane.getCursor() == javafx.scene.Cursor.W_RESIZE) {
                pStage.setX(pStage.getX() + deltaX);
                pStage.setWidth(pStage.getWidth() - deltaX);
            }
            if (pPane.getCursor() == javafx.scene.Cursor.NW_RESIZE || pPane.getCursor() == javafx.scene.Cursor.N_RESIZE) {
                pStage.setY(pStage.getY() + deltaY);
                pStage.setHeight(pStage.getHeight() - deltaY);
            }
            if (pPane.getCursor() == javafx.scene.Cursor.NE_RESIZE || pPane.getCursor() == javafx.scene.Cursor.E_RESIZE) {
                pStage.setWidth(pStage.getWidth() + deltaX);
            }
            if (pPane.getCursor() == javafx.scene.Cursor.SE_RESIZE || pPane.getCursor() == javafx.scene.Cursor.S_RESIZE) {
                pStage.setHeight(pStage.getHeight() + deltaY);
            }
            if (pPane.getCursor() == javafx.scene.Cursor.SE_RESIZE || pPane.getCursor() == javafx.scene.Cursor.S_RESIZE) {
                pStage.setWidth(pStage.getWidth() + deltaX);
                pStage.setHeight(pStage.getHeight() + deltaY);
            }
            startX = pEvent.getScreenX();
            startY = pEvent.getScreenY();
        }
    }

    private boolean isInResizeMargin(MouseEvent pEvent, Pane pPane) {
        return pEvent.getX() < RESIZE_MARGIN || pEvent.getX() > pPane.getWidth() - RESIZE_MARGIN
                || pEvent.getY() < RESIZE_MARGIN || pEvent.getY() > pPane.getHeight() - RESIZE_MARGIN;
    }

    private boolean isInLeftResizeZone(MouseEvent pEvent) {
        return pEvent.getX() < RESIZE_MARGIN;
    }

    private boolean isInRightResizeZone(MouseEvent pEvent, Pane pPane) {
        return pEvent.getX() > pPane.getWidth() - RESIZE_MARGIN;
    }

    private boolean isInTopResizeZone(MouseEvent pEvent) {
        return pEvent.getY() < RESIZE_MARGIN;
    }

    private boolean isInBottomResizeZone(MouseEvent pEvent, Pane pPane) {
        return pEvent.getY() > pPane.getHeight() - RESIZE_MARGIN;
    }

    private boolean isInTopLeftCorner(MouseEvent pEvent) {
        return isInLeftResizeZone(pEvent) && isInTopResizeZone(pEvent);
    }

    private boolean isInTopRightCorner(MouseEvent pEvent, Pane pPane) {
        return isInRightResizeZone(pEvent, pPane) && isInTopResizeZone(pEvent);
    }

    private boolean isInBottomLeftCorner(MouseEvent pEvent, Pane pPane) {
        return isInLeftResizeZone(pEvent) && isInBottomResizeZone(pEvent, pPane);
    }

    private boolean isInBottomRightCorner(MouseEvent pEvent, Pane pPane) {
        return isInRightResizeZone(pEvent, pPane) && isInBottomResizeZone(pEvent, pPane);
    }
}
