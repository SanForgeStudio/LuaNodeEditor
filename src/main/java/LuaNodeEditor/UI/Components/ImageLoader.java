package LuaNodeEditor.UI.Components;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.InputStream;

public class ImageLoader extends HBox {

    public ImageLoader(String pImagePath, int pWidth, int pHeight) {
        InputStream imageStream = getClass().getResourceAsStream(pImagePath);
        if (imageStream != null) {
            Image image = new Image(imageStream, pWidth, pHeight, true, true);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(pWidth);
            imageView.setFitHeight(pHeight);
            getChildren().add(imageView);
            setSpacing(5);
            setAlignment(Pos.CENTER);
        } else {
            System.out.println("Image not found: " + pImagePath);
        }
    }

    public static Image getImage(String pImagePath) {
        InputStream imageStream = ImageLoader.class.getResourceAsStream(pImagePath);
        if (imageStream != null) {
            return new Image(imageStream);
        } else {
            System.out.println("Image not found: " + pImagePath);
            return null;
        }
    }
}
