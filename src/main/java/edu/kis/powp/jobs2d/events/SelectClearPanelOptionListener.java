package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import edu.kis.powp.jobs2d.features.DrawerFeature;

import javax.swing.*;

public class SelectClearPanelOptionListener implements ActionListener {

    JPanel panel = DrawerFeature.getPanel();

    @Override
    public void actionPerformed(ActionEvent e) {
        DrawerFeature.getDrawerController().clearPanel();
        cleanMouseListeners();
    }

    private void cleanMouseListeners(){
        Arrays.stream(panel
                .getMouseListeners())
                .forEach(panel::removeMouseListener);
    }

}
