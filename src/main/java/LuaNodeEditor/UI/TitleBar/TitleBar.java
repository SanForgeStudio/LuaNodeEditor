package LuaNodeEditor.UI.TitleBar;

import javax.swing.*;
import java.awt.*;

import LuaNodeEditor.UI.Components.*;
import LuaNodeEditor.UI.Listeners.MouseDragListener;
import LuaNodeEditor.UI.TitleBar.Buttons.*;

public class TitleBar extends BasePanel {

    public TitleBar(JFrame pFrame) {
        super(new Color(45, 45, 45), 30);

        ImageLoader logoLoader = new ImageLoader("/assets/images/logo.png", 25, 25);
        add(logoLoader, BorderLayout.WEST);

        add(new TextLabel("Lua Node Editor"), BorderLayout.CENTER);

        ButtonPanel buttonPanel = new ButtonPanel(pFrame);
        add(buttonPanel, BorderLayout.EAST);

        MouseDragListener dragListener = new MouseDragListener(pFrame);
        addMouseListener(dragListener);
        addMouseMotionListener(dragListener);
    }
}
