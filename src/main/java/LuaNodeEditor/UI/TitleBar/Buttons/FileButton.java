package LuaNodeEditor.UI.TitleBar.Buttons;

import LuaNodeEditor.UI.Components.BasePopupMenu;
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

        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As");

        newItem.setOnAction(event -> new NewAction().actionPerformed(null));
        openItem.setOnAction(event -> new OpenAction().actionPerformed(null));
        saveItem.setOnAction(event -> new SaveAction().actionPerformed(null));
        saveAsItem.setOnAction(event -> new SaveAsAction().actionPerformed(null));

        BasePopupMenu contextMenu = new BasePopupMenu(Color.rgb(50, 50, 50), Color.rgb(255, 255, 255), Color.rgb(80, 80, 80), newItem, openItem, saveItem, saveAsItem);

        //TODO: Doesn't work in Non Fullscreen mode
        setOnAction(event -> {
            contextMenu.show(FileButton.this, getLayoutX(), getLayoutY() + getHeight());
        });
    }
}
