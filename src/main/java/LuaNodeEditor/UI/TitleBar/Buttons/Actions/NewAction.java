package LuaNodeEditor.UI.TitleBar.Buttons.Actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent pEvent) {
        System.out.println("New file action triggered");
    }
}
