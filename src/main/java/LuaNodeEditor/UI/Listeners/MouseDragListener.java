package LuaNodeEditor.UI.Listeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseDragListener extends MouseAdapter {

    private final JFrame frame;
    private Point initialClick;

    public MouseDragListener(JFrame pFrame) {
        this.frame = pFrame;
    }

    @Override
    public void mousePressed(MouseEvent pEvent) {
        initialClick = pEvent.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent pEvent) {
        int thisX = frame.getLocation().x;
        int thisY = frame.getLocation().y;

        int xMoved = pEvent.getX() - initialClick.x;
        int yMoved = pEvent.getY() - initialClick.y;

        int newX = thisX + xMoved;
        int newY = thisY + yMoved;

        frame.setLocation(newX, newY);
    }
}
