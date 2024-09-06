package LuaNodeEditor.UI.Helpers;

import java.awt.*;

public class GridBagHelper {

    public static GridBagConstraints createStandardConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = GridBagConstraints.RELATIVE;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.CENTER;
        return gbc;
    }
}
