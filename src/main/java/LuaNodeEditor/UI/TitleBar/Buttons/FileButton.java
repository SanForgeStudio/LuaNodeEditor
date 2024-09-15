package LuaNodeEditor.UI.TitleBar.Buttons;

import LuaNodeEditor.UI.TitleBar.Buttons.Actions.NewAction;
import LuaNodeEditor.UI.TitleBar.Buttons.Actions.OpenAction;
import LuaNodeEditor.UI.TitleBar.Buttons.Actions.SaveAction;
import LuaNodeEditor.UI.TitleBar.Buttons.Actions.SaveAsAction;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Window;

public class FileButton extends Button {

    public FileButton(Window pWindow) {
        setText("File");
        setFont(new Font(12));
        setTextFill(Color.WHITE);
        setPadding(new Insets(5, 10, 5, 10));

        setBackground(new Background(new BackgroundFill(Color.rgb(45, 45, 45), new CornerRadii(10), Insets.EMPTY)));

        setOnMouseEntered(event -> setBackground(new Background(new BackgroundFill(Color.rgb(80, 80, 80), new CornerRadii(10), Insets.EMPTY))));
        setOnMouseExited(event -> setBackground(new Background(new BackgroundFill(Color.rgb(45, 45, 45), new CornerRadii(10), Insets.EMPTY))));

        ContextMenu contextMenu = createContextMenu();

        setOnAction(event -> {
            double x = localToScreen(getBoundsInLocal()).getMinX();
            double y = localToScreen(getBoundsInLocal()).getMaxY();
            contextMenu.show(FileButton.this, x, y);
        });
    }

    private ContextMenu createContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As");

        newItem.setOnAction(event -> NewAction.handleNewAction());
        openItem.setOnAction(event -> OpenAction.handleOpenAction());
        saveItem.setOnAction(event -> SaveAction.handleSaveAction());
        saveAsItem.setOnAction(event -> SaveAsAction.handleSaveAsAction());

        contextMenu.getItems().addAll(newItem, openItem, saveItem, saveAsItem);

        return contextMenu;
    }
}
