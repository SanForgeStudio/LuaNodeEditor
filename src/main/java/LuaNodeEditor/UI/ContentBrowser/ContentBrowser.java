package LuaNodeEditor.UI.ContentBrowser;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;

import java.io.File;

public class ContentBrowser extends VBox {

    private static final TreeView<String> treeView = new TreeView<>();

    public ContentBrowser() {
        getChildren().add(treeView);
        setStyle("-fx-background-color: #2e2e2e; -fx-padding: 10;");
    }

    public static void updateContent(File pFolder) {
        TreeItem<String> rootItem = new TreeItem<>(pFolder.getName());
        rootItem.setExpanded(true);
        populateTree(rootItem, pFolder);
        treeView.setRoot(rootItem);
    }

    private static void populateTree(TreeItem<String> pParentItem, File pFolder) {
        File[] files = pFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                TreeItem<String> item = new TreeItem<>(file.getName());
                pParentItem.getChildren().add(item);
                if (file.isDirectory()) {
                    populateTree(item, file);
                }
            }
        }
    }
}
