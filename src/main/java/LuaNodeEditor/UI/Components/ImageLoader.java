package LuaNodeEditor.UI.Components;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageLoader extends JLabel {

    public ImageLoader(String pImagePath, int pWidth, int pHeight) {
        URL imageURL = getClass().getResource(pImagePath);
        if (imageURL != null) {
            ImageIcon icon = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(pWidth, pHeight, Image.SCALE_SMOOTH));
            setIcon(icon);
            setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 5));
        } else {
            System.out.println("Image not found: " + pImagePath);
        }
    }

    public Image getImage(String pImagePath) {
        URL imageURL = getClass().getResource(pImagePath);
        if (imageURL != null) {
            return new ImageIcon(imageURL).getImage();
        } else {
            System.out.println("Image not found: " + pImagePath);
            return null;
        }
    }
}
