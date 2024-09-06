package LuaNodeEditor;

import LuaNodeEditor.UI.Components.ImageLoader;
import LuaNodeEditor.UI.ResizableWindow;
import LuaNodeEditor.UI.MainPanel;
import LuaNodeEditor.UI.TitleBar.TitleBar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class LuaNodeEditor {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Lua Node Editor");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800);
            frame.setUndecorated(true);
            frame.setLocationRelativeTo(null);
            frame.getContentPane().setBackground(new Color(35, 35, 35));

            ImageLoader imageLoader = new ImageLoader("/assets/images/logo.png", 25, 25);
            Image image = imageLoader.getImage("/assets/images/logo.png");
            frame.setIconImage(image);

            new ResizableWindow(frame);

            TitleBar titleBar = new TitleBar(frame);
            MainPanel mainPanel = new MainPanel();

            frame.getContentPane().add(titleBar, BorderLayout.NORTH);
            frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }
}
