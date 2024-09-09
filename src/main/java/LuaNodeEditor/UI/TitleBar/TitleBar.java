package LuaNodeEditor.UI.TitleBar;

import LuaNodeEditor.UI.Components.ImageLoader;
import LuaNodeEditor.UI.TitleBar.Buttons.ButtonPanel;
import LuaNodeEditor.UI.TitleBar.Buttons.FileButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class TitleBar extends BorderPane {

    public TitleBar(Stage pStage) {
        setStyle("-fx-background-color: #2D2D2D;");
        setPrefHeight(30);

        HBox leftPanel = new HBox();
        leftPanel.setPadding(new Insets(0, 10, 0, 10));
        leftPanel.setSpacing(10);
        leftPanel.setAlignment(Pos.CENTER_LEFT);

        HBox contentContainer = new HBox();
        contentContainer.setAlignment(Pos.CENTER_LEFT);

        ImageLoader logoLoader = new ImageLoader("/assets/images/logo.png", 30, 30);
        contentContainer.getChildren().add(logoLoader);

        FileButton fileButton = new FileButton(pStage);
        contentContainer.getChildren().add(fileButton);

        leftPanel.getChildren().add(contentContainer);

        ButtonPanel buttonPanel = new ButtonPanel(pStage);
        setRight(buttonPanel);
        setLeft(leftPanel);

    }
}
