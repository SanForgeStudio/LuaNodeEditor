package LuaNodeEditor.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResizableWindow {

    private static final int BORDER_DRAG_THICKNESS = 5;
    private final JFrame frame;
    private boolean resizing = false;

    public ResizableWindow(JFrame pFrame) {
        this.frame = pFrame;
        addResizeListeners();
    }

    private void addResizeListeners() {
        frame.getRootPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent pEvent) {
                if (isInResizableArea(pEvent.getPoint())) {
                    resizing = true;
                    frame.setCursor(getResizeCursor(pEvent.getPoint()));
                }
            }

            @Override
            public void mouseReleased(MouseEvent pEvent) {
                resizing = false;
                frame.setCursor(Cursor.getDefaultCursor());
            }
        });

        frame.getRootPane().addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent pEvent) {
                if (isInResizableArea(pEvent.getPoint())) {
                    frame.setCursor(getResizeCursor(pEvent.getPoint()));
                } else {
                    frame.setCursor(Cursor.getDefaultCursor());
                }
            }

            @Override
            public void mouseDragged(MouseEvent pEvent) {
                if (resizing) {
                    resizeWindow(pEvent);
                }
            }
        });
    }

    private boolean isInResizableArea(Point pPoint) {
        int width = frame.getWidth();
        int height = frame.getHeight();

        boolean isLeftEdge = pPoint.x < BORDER_DRAG_THICKNESS;
        boolean isRightEdge = pPoint.x > width - BORDER_DRAG_THICKNESS;
        boolean isTopEdge = pPoint.y < BORDER_DRAG_THICKNESS;
        boolean isBottomEdge = pPoint.y > height - BORDER_DRAG_THICKNESS;

        return isLeftEdge || isRightEdge || isTopEdge || isBottomEdge;
    }


    private Cursor getResizeCursor(Point pPoint) {
        int width = frame.getWidth();
        int height = frame.getHeight();

        if (pPoint.x < BORDER_DRAG_THICKNESS) {
            return pPoint.y < BORDER_DRAG_THICKNESS ? Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR) :
                    pPoint.y > height - BORDER_DRAG_THICKNESS ? Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR) :
                            Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
        } else if (pPoint.x > width - BORDER_DRAG_THICKNESS) {
            return pPoint.y < BORDER_DRAG_THICKNESS ? Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR) :
                    pPoint.y > height - BORDER_DRAG_THICKNESS ? Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR) :
                            Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
        } else if (pPoint.y < BORDER_DRAG_THICKNESS) {
            return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
        } else if (pPoint.y > height - BORDER_DRAG_THICKNESS) {
            return Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
        }
        return Cursor.getDefaultCursor();
    }

    private void resizeWindow(MouseEvent pEvent) {
        int dx = pEvent.getXOnScreen() - frame.getX();
        int dy = pEvent.getYOnScreen() - frame.getY();

        switch (frame.getCursor().getType()) {
            case Cursor.NW_RESIZE_CURSOR:
                frame.setBounds(frame.getX() + dx, frame.getY() + dy, frame.getWidth() - dx, frame.getHeight() - dy);
                break;
            case Cursor.NE_RESIZE_CURSOR:
                frame.setBounds(frame.getX(), frame.getY() + dy, dx, frame.getHeight() - dy);
                break;
            case Cursor.SW_RESIZE_CURSOR:
                frame.setBounds(frame.getX() + dx, frame.getY(), frame.getWidth() - dx, dy);
                break;
            case Cursor.SE_RESIZE_CURSOR:
                frame.setSize(dx, dy);
                break;
            case Cursor.W_RESIZE_CURSOR:
                frame.setBounds(frame.getX() + dx, frame.getY(), frame.getWidth() - dx, frame.getHeight());
                break;
            case Cursor.E_RESIZE_CURSOR:
                frame.setSize(dx, frame.getHeight());
                break;
            case Cursor.N_RESIZE_CURSOR:
                frame.setBounds(frame.getX(), frame.getY() + dy, frame.getWidth(), frame.getHeight() - dy);
                break;
            case Cursor.S_RESIZE_CURSOR:
                frame.setSize(frame.getWidth(), dy);
                break;
        }
    }
}