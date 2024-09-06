package LuaNodeEditor;

import javax.swing.*;
import java.awt.*;

import LuaNodeEditor.UI.MainPanel;
import LuaNodeEditor.UI.TitleBar.TitleBar;
import java.net.URL;

public class LuaNodeEditor {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Lua Node Editor");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800);
            frame.setLocationRelativeTo(null);
            frame.setUndecorated(true);
            frame.getContentPane().setBackground(new Color(35, 35, 35));

            // Set the application icon
            URL iconURL = LuaNodeEditor.class.getResource("/assets/images/logo.png");
            if (iconURL != null) {
                frame.setIconImage(new ImageIcon(iconURL).getImage());
            }

            // Create and add the custom title bar
            TitleBar titleBar = new TitleBar(frame);
            frame.getContentPane().add(titleBar, BorderLayout.NORTH);

            // Create and add the main panel for the content
            MainPanel mainPanel = new MainPanel();
            frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }
}
