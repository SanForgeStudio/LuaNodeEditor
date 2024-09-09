package LuaNodeEditor.UI.TitleBar.Buttons.Actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent pEvent) {
        System.out.println("Load file action triggered");
    }
}
