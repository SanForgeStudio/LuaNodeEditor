package LuaNodeEditor.UI.Components;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;

public class CustomFolderChooser extends JFrame {
    private final JList<File> folderList;
    private final DefaultListModel<File> folderListModel;
    private final JTextField pathField;

    public CustomFolderChooser() {
        setTitle("Custom Folder Chooser");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        pathField = new JTextField();
        pathField.setEditable(false);
        contentPane.add(pathField, BorderLayout.NORTH);

        folderListModel = new DefaultListModel<>();
        folderList = new JList<>(folderListModel);
        folderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane folderScrollPane = new JScrollPane(folderList);
        contentPane.add(folderScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton openButton = new JButton("Open");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(openButton);
        buttonPanel.add(cancelButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        loadFolders(FileSystemView.getFileSystemView().getHomeDirectory());

        openButton.addActionListener(e -> openFolder());

        cancelButton.addActionListener(e -> dispose());

        folderList.addListSelectionListener(e -> updatePathField());
    }

    private void loadFolders(File directory) {
        folderListModel.clear();
        File[] folders = directory.listFiles(File::isDirectory);
        if (folders != null) {
            for (File folder : folders) {
                folderListModel.addElement(folder);
            }
        }
        pathField.setText(directory.getAbsolutePath());
    }

    private void openFolder() {
        File selectedFolder = folderList.getSelectedValue();
        if (selectedFolder != null) {
            JOptionPane.showMessageDialog(this, "Selected Folder: " + selectedFolder.getAbsolutePath());
        }
    }

    private void updatePathField() {
        File selectedFolder = folderList.getSelectedValue();
        if (selectedFolder != null && selectedFolder.isDirectory()) {
            loadFolders(selectedFolder);
        }
    }

    public static void showFolderChooser() {
        SwingUtilities.invokeLater(() -> new CustomFolderChooser().setVisible(true));
    }
}
