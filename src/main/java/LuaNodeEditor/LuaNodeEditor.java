package LuaNodeEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LuaNodeEditor {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Lua Node Editor");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800);
            frame.setLocationRelativeTo(null);
            frame.setUndecorated(true);
            frame.getContentPane().setBackground(new Color(35, 35, 35));

            // Create a custom title bar
            JPanel titleBar = new JPanel();
            titleBar.setBackground(new Color(45, 45, 45));
            titleBar.setLayout(new BorderLayout());

            // Create a logo (can be an ImageIcon or JLabel with an image)
            JLabel logoLabel = new JLabel(new ImageIcon("path_to_logo_image")); // replace with your logo path
            titleBar.add(logoLabel, BorderLayout.WEST);

            // Create a title label
            JLabel titleLabel = new JLabel("Lua Node Editor");
            titleLabel.setForeground(Color.WHITE);
            titleBar.add(titleLabel, BorderLayout.CENTER);

            // Create buttons for minimize, maximize, and close
            JPanel buttonPanel = new JPanel();
            buttonPanel.setOpaque(false);
            buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

            JButton minimizeButton = new JButton("-");
            minimizeButton.setForeground(Color.WHITE);
            minimizeButton.setBackground(new Color(60, 60, 60));
            minimizeButton.setBorder(BorderFactory.createEmptyBorder());
            minimizeButton.setPreferredSize(new Dimension(40, 30));
            minimizeButton.setFocusPainted(false);
            minimizeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setState(JFrame.ICONIFIED);
                }
            });

            JButton maximizeButton = new JButton("+");
            maximizeButton.setForeground(Color.WHITE);
            maximizeButton.setBackground(new Color(60, 60, 60));
            maximizeButton.setBorder(BorderFactory.createEmptyBorder());
            maximizeButton.setPreferredSize(new Dimension(40, 30));
            maximizeButton.setFocusPainted(false);
            maximizeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (frame.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                        frame.setExtendedState(JFrame.NORMAL);
                    } else {
                        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    }
                }
            });

            JButton closeButton = new JButton("x");
            closeButton.setForeground(Color.WHITE);
            closeButton.setBackground(new Color(60, 60, 60));
            closeButton.setBorder(BorderFactory.createEmptyBorder());
            closeButton.setPreferredSize(new Dimension(40, 30));
            closeButton.setFocusPainted(false);
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });

            buttonPanel.add(minimizeButton);
            buttonPanel.add(maximizeButton);
            buttonPanel.add(closeButton);

            titleBar.add(buttonPanel, BorderLayout.EAST);

            // Add the title bar to the frame
            frame.getContentPane().add(titleBar, BorderLayout.NORTH);

            // Create the main panel for the content
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setOpaque(false);

            JLabel contentLabel = new JLabel("Content Area", SwingConstants.CENTER);
            contentLabel.setForeground(Color.WHITE);

            mainPanel.add(contentLabel, BorderLayout.CENTER);

            frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}