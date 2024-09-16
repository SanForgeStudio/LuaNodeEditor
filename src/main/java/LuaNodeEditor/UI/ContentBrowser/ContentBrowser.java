package LuaNodeEditor.UI.ContentBrowser;

import LuaNodeEditor.LuaNodeEditor;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.File;

public class ContentBrowser extends VBox {

    private final TreeView<String> treeView;

    public ContentBrowser() {
        treeView = new TreeView<>();
        treeView.setStyle("-fx-background-color: #2e2e2e;");

        treeView.setCellFactory(new Callback<>() {
            @Override
            public TreeCell<String> call(TreeView<String> param) {
                return new TreeCell<>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setStyle("");
                        } else {
                            setText(item);
                            setStyle("-fx-text-fill: white; -fx-background-color: #2e2e2e;");
                        }
                    }
                };
            }
        });

        getChildren().add(treeView);
        setStyle("-fx-background-color: #2e2e2e; -fx-padding: 10; -fx-end-margin: 20");
    }

    public void updateContent(File folder) {
        if (folder != null && folder.exists() && folder.isDirectory()) {
            TreeItem<String> rootItem = new TreeItem<>(folder.getName());
            rootItem.setExpanded(true);
            populateTree(rootItem, folder);
            treeView.setRoot(rootItem);
            LuaNodeEditor.showContentBrowser();
        } else {
            collapseContent();
        }
    }

    private void populateTree(TreeItem<String> parentItem, File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                TreeItem<String> item = new TreeItem<>(file.getName());
                parentItem.getChildren().add(item);
                if (file.isDirectory()) {
                    populateTree(item, file);
                }
            }
        }
    }

    private void collapseContent() {
        treeView.setRoot(null);
        LuaNodeEditor.hideContentBrowser();
    }
}