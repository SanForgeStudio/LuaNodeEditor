package LuaNodeEditor.UI.ContentBrowser;

import javafx.scene.control.TreeView;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.VBox;
import java.io.File;

public class ContentBrowser extends VBox {

    private static final TreeView<String> treeView = new TreeView<>();

    public ContentBrowser() {
        getChildren().add(treeView);
        setStyle("-fx-background-color: #2e2e2e; -fx-padding: 10;");
    }

    public static void updateContent(File folder) {
        TreeItem<String> rootItem = new TreeItem<>(folder.getName());
        rootItem.setExpanded(true);
        populateTree(rootItem, folder);
        treeView.setRoot(rootItem);
    }

    private static void populateTree(TreeItem<String> parentItem, File folder) {
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
}
