package LuaNodeEditor.UI.TitleBar;

import LuaNodeEditor.UI.Components.ImageLoader;
import LuaNodeEditor.UI.TitleBar.Buttons.ControlButtons.ButtonPanel;
import LuaNodeEditor.UI.TitleBar.Buttons.FileButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TitleBar extends BorderPane {

    private final StackPane stackPane;

    public TitleBar(Stage pStage) {
        setStyle("-fx-background-color: #2D2D2D;");
        setPrefHeight(30);

        HBox leftPanel = new HBox();
        leftPanel.setPadding(new Insets(0, 5, 0, 5));
        leftPanel.setSpacing(10);
        leftPanel.setAlignment(Pos.CENTER_LEFT);
        HBox contentContainer = new HBox();
        contentContainer.setAlignment(Pos.CENTER_LEFT);

        ImageLoader logoLoader = new ImageLoader("/assets/images/logo.png", 40, 40);
        logoLoader.setPadding(new Insets(0, 5, 0, 5));
        contentContainer.getChildren().add(logoLoader);

        FileButton fileButton = new FileButton(pStage);
        contentContainer.getChildren().add(fileButton);

        leftPanel.getChildren().add(contentContainer);

        stackPane = new StackPane();
        ButtonPanel buttonPanel = new ButtonPanel(pStage);
        stackPane.getChildren().addAll(buttonPanel);
        stackPane.setAlignment(Pos.TOP_RIGHT);
        setCenter(stackPane);

        updatePadding(pStage.getWidth());

        pStage.widthProperty().addListener((observable, oldValue, newValue) -> updatePadding(newValue.doubleValue()));

        setLeft(leftPanel);
    }

    // TODO: This Method is really inefficient, it should be Optimized and fixed
    private void updatePadding(double pWindowWidth) {
        if (stackPane != null) {
            double leftPadding = Math.max(0, pWindowWidth - 250);
            stackPane.setPadding(new Insets(0, 0, 0, leftPadding));
        }
    }
}

