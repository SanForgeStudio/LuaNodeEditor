package LuaNodeEditor.UI.Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Stack;

public class FolderChooser extends JFrame {
    private final JTree folderTree;
    private final DefaultTreeModel treeModel;
    private final JTextField pathField;
    private final Stack<File> backStack = new Stack<>();
    private final Stack<File> forwardStack = new Stack<>();
    private File currentDirectory;

    public FolderChooser() {
        setTitle("Select a Folder");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Image image = ImageLoader.getImage("/assets/images/logo.png");
        setIconImage(image);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(new Color(35, 35, 35));  // Dark background
        setContentPane(contentPane);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBackground(new Color(45, 45, 45));  // Darker toolbar
        JButton backButton = new JButton("Back");
        JButton forwardButton = new JButton("Forward");
        JButton upButton = new JButton("Up");
        toolBar.add(backButton);
        toolBar.add(forwardButton);
        toolBar.add(upButton);
        contentPane.add(toolBar, BorderLayout.NORTH);

        // Top Panel with Text Field
        pathField = new JTextField();
        pathField.setEditable(true);
        pathField.setBackground(Color.WHITE);
        pathField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.add(pathField, BorderLayout.NORTH);

        // Tree to show folders and files
        File rootDirectory = getRootDirectory();
        treeModel = new DefaultTreeModel(createNodes(rootDirectory));
        folderTree = new JTree(treeModel);
        folderTree.setCellRenderer(new FileTreeCellRenderer());
        folderTree.setBackground(new Color(45, 45, 45));
        folderTree.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane folderScrollPane = new JScrollPane(folderTree);
        contentPane.add(folderScrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(35, 35, 35));
        JButton openButton = new JButton("Open");
        styleButton(openButton);
        JButton cancelButton = new JButton("Cancel");
        styleButton(cancelButton);
        buttonPanel.add(openButton);
        buttonPanel.add(cancelButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        openButton.addActionListener(e -> openFolder());
        cancelButton.addActionListener(e -> dispose());
        pathField.addActionListener(e -> handlePathInput());

        backButton.addActionListener(e -> navigateBack());
        forwardButton.addActionListener(e -> navigateForward());
        upButton.addActionListener(e -> navigateUp());
    }

    private DefaultMutableTreeNode createNodes(File root) {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(root);
        File[] files = root.listFiles();
        if (files != null) {
            for (File file : files) {
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(file);
                if (file.isDirectory()) {
                    childNode.add(new DefaultMutableTreeNode("Loading..."));
                }
                rootNode.add(childNode);
            }
        }
        return rootNode;
    }

    private void loadFolders(File directory) {
        if (currentDirectory != null && !backStack.contains(currentDirectory)) {
            backStack.push(currentDirectory);
        }
        currentDirectory = directory;
        treeModel.setRoot(createNodes(directory));
        pathField.setText(directory.getAbsolutePath());
    }

    private void openFolder() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) folderTree.getLastSelectedPathComponent();
        if (selectedNode != null) {
            File selectedFile = (File) selectedNode.getUserObject();
            JOptionPane.showMessageDialog(this, "Selected: " + selectedFile.getAbsolutePath());
        }
    }

    private void handlePathInput() {
        File typedPath = new File(pathField.getText());
        if (typedPath.exists()) {
            loadFolders(typedPath);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid path!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void navigateBack() {
        if (!backStack.isEmpty()) {
            forwardStack.push(currentDirectory);
            loadFolders(backStack.pop());
        }
    }

    private void navigateForward() {
        if (!forwardStack.isEmpty()) {
            backStack.push(currentDirectory);
            loadFolders(forwardStack.pop());
        }
    }

    private void navigateUp() {
        if (currentDirectory != null && currentDirectory.getParentFile() != null) {
            loadFolders(currentDirectory.getParentFile());
        }
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(240, 240, 240));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    private static class FileTreeCellRenderer extends DefaultTreeCellRenderer {
        private final Icon folderIcon;
        private final Icon fileIcon;

        public FileTreeCellRenderer() {
            folderIcon = UIManager.getIcon("FileView.directoryIcon");
            fileIcon = UIManager.getIcon("FileView.fileIcon");
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            File file = (File) node.getUserObject();

            if (file.isDirectory()) {
                label.setIcon(folderIcon);
            } else {
                label.setIcon(fileIcon);
            }

            label.setBackground(new Color(45, 45, 45));
            label.setForeground(Color.BLACK);
            return label;
        }
    }

    public static void showFolderChooser() {
        SwingUtilities.invokeLater(() -> new FolderChooser().setVisible(true));
    }

    private File getRootDirectory() {
        Path rootPath = FileSystems.getDefault().getRootDirectories().iterator().next();
        return rootPath.toFile();
    }
}
