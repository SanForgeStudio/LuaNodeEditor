package LuaNodeEditor.UI.Helpers;

import java.awt.*;

public class GridBagHelper {

    public static GridBagConstraints createStandardConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = GridBagConstraints.RELATIVE;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        return gbc;
    }

    public static GridBagConstraints createConstraints(int pGridX, int pGridY, int pFill, int pAnchor, Insets pInsets) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = pGridX;
        gbc.gridy = pGridY;
        gbc.fill = pFill;
        gbc.anchor = pAnchor;
        gbc.insets = pInsets;
        return gbc;
    }
}
