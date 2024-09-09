package LuaNodeEditor.UI.TitleBar.Buttons;

import LuaNodeEditor.UI.TitleBar.Buttons.Actions.*;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Window;

public class FileButton extends Button {

    public FileButton(Window pWindow) {
        setText("File");
        setFont(new Font(12));
        setTextFill(Color.WHITE);
        setBackground(new Background(new BackgroundFill(Color.rgb(45, 45, 45), new CornerRadii(10), Insets.EMPTY)));

        setOnMouseEntered(event -> setBackground(new Background(new BackgroundFill(Color.rgb(80, 80, 80), new CornerRadii(10), Insets.EMPTY))));
        setOnMouseExited(event -> setBackground(new Background(new BackgroundFill(Color.rgb(45, 45, 45), new CornerRadii(10), Insets.EMPTY))));

        ContextMenu contextMenu = new ContextMenu();
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As");

        newItem.setOnAction(event -> new NewAction().actionPerformed(null));
        openItem.setOnAction(event -> new OpenAction().actionPerformed(null));
        saveItem.setOnAction(event -> new SaveAction().actionPerformed(null));
        saveAsItem.setOnAction(event -> new SaveAsAction().actionPerformed(null));

        contextMenu.getItems().addAll(newItem, openItem, saveItem, saveAsItem);

        setOnAction(event -> contextMenu.show(FileButton.this, 0, FileButton.this.getHeight()));
    }
}
