package LuaNodeEditor.Utils;

import javafx.geometry.Insets;

public class InsetsUtils extends Insets {

    public InsetsUtils(double top, double right, double bottom, double left) {
        super(top, right, bottom, left);
    }

    public InsetsUtils(double value) {
        super(value);
    }

    public InsetsUtils(double vertical, double horizontal) {
        super(vertical, horizontal, vertical, horizontal);
    }
}