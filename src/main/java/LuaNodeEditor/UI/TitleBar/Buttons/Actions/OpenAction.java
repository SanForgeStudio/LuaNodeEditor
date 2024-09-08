package LuaNodeEditor.UI.TitleBar.Buttons.Actions;

import LuaNodeEditor.UI.Components.CustomFolderChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent pEvent) {
        CustomFolderChooser.showFolderChooser();
    }
}
